package com.cte.drools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@ManagedBean(name = "viewBean")
@SessionScoped
public class View implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger logger = LoggerFactory.getLogger( View.class );

	private ConcurrentHashMap<String,Question> questions = new ConcurrentHashMap<String,Question>();
	private ConcurrentHashMap<String,Form> forms = new ConcurrentHashMap<String,Form>();
	private boolean complete;
	
	public View() {
		reset();
	}
	
	public void reset() {
		questions = new ConcurrentHashMap<String,Question>();
		forms = new ConcurrentHashMap<String,Form>();
		this.addQuestion("Are you a resident of B.C.");

		this.addQuestion("Are you submitting this as a individual");
		
		this.addQuestion("Do you like dogs ?");
	}
	
	public void addQuestion(String question){
		logger.debug("adding '"+question+"'");
		Question q = new Question();
		q.setQuestion(question);
		if (!questions.containsKey(question))	questions.put(question,q);
	}
	
	public void removeQuestion(String question){
		if (questions.containsKey(question)) {
			logger.error("removing '"+question+"'");
			questions.remove(question);
		}
	}
	
	public void addForm(String form, String title, String bean){
		
		Form f = new Form();
		f.setName(form);
		f.setTitle(title);
		f.setBean(bean);
		if (!forms.contains(f)) {
			logger.error("adding"+form);
			forms.put(form,f);
		}
	}
	public void removeForm(String form){
		if (forms.containsKey(form)){
			logger.error("removing"+form);
			forms.remove(form);
		}
	}
	
	public List<Question> getQuestions() {
		
		return  new ArrayList<Question>(questions.values());
	}
	
public List<Form> getForms() {
	    ArrayList<Form> formsList = new ArrayList<Form>(forms.values());
	    Collections.sort(formsList);
		return  formsList;
	}
	public boolean isComplete() {
		complete = true;
		for (Question question: questions.values()) {
			if (!question.isAnswered()) {
				complete=false;
				break;
			}
		}
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	public void submit(){
		System.out.println("submit view");
		ArrayList<Submittable> submit = new ArrayList<Submittable>();
		for (Form form: this.getForms()) {
			Submittable s = findBean (form.getBean());
			if (s != null) {
				if (!submit.contains(s)) submit.add(s);
			}
		}
		for (Submittable s: submit){
			s.submit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    if (context == null) logger.error("FACES CONTEXT is NULL");
	    Object o = context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
	    return (T) o;
	}
	
	
}
