package com.cte.drools;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

@ManagedBean(name = "driverBean")
@SessionScoped
public class Driver implements Serializable {

	 public void process(View view) {
	        try {
	            // load up the knowledge base
	            KnowledgeBase kbase = readKnowledgeBase();
	            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
	            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "demo");
	            // go !
	            ksession.setGlobal("view", view);
	            for (Question question: view.getQuestions()) {
	             ksession.insert(question);
	            }
	            ksession.fireAllRules();
	            logger.close();
	        } catch (Throwable t) {
	            t.printStackTrace();
	        }
	    }
	 
	 
	    private static KnowledgeBase readKnowledgeBase() throws Exception {
	        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
	        kbuilder.add(ResourceFactory.newClassPathResource("Sample.drl"), ResourceType.DRL);
	        KnowledgeBuilderErrors errors = kbuilder.getErrors();
	        if (errors.size() > 0) {
	            for (KnowledgeBuilderError error: errors) {
	                System.err.println(error);
	            }
	            throw new IllegalArgumentException("Could not parse knowledge.");
	        }
	        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
	        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	        return kbase;
	    }


}
