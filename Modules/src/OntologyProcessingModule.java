
/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// I have modified the code from obtained from goo.gl/vNICWe (thanks to Ignazio Palmisano). These modifications are done to complete my academic project work.  
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Set;
import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxEditorParser;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.expression.OWLEntityChecker;
import org.semanticweb.owlapi.expression.ParserException;
import org.semanticweb.owlapi.expression.ShortFormEntityChecker;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.BidirectionalShortFormProvider;
import org.semanticweb.owlapi.util.BidirectionalShortFormProviderAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

public class OntologyProcessingModule{
	public String[] getObjectProperty1(String objName) throws IOException{
		return null;
	}
	String [] getObjectProperty(String s) throws OWLOntologyCreationException, FileNotFoundException, UnsupportedEncodingException{
        // Load an example ontology.
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        System.out.println("b4 IRI---");
        IRI ontologyIRI =IRI.create("file:///home/rupam/Documents/JavaPhDCode/ICAART/src/OPrO.owl"); 
        System.out.println("after IRI---");
        OWLOntology ontology = manager.loadOntology(ontologyIRI);
        // We need a reasoner to do our query answering
        // This example uses HermiT: http://hermit-reasoner.com/
        OWLReasoner reasoner = new Reasoner.ReasonerFactory().createReasoner(ontology);
        ShortFormProvider shortFormProvider = new SimpleShortFormProvider();
        // Create the DLQPrinter helper class. This will manage the
        // parsing of input and printing of results
        DLQPrinter DLQPrinter = new DLQPrinter(new DLQEngine(reasoner,
                shortFormProvider), shortFormProvider);
        // Enter the query loop. A user is expected to enter class
        // expression on the command line.
        int countQuery=0;
        System.out.println("Extract the object name from the Frame");
        String objectName=s; 
         if(s.equals("box")){
        	objectName="CerealBox"; 
         }
         else if(s.equals("microwave")){
        	 objectName="MicrowaveOven";
         }
         else if(s.equals("book")){
        	 objectName="Book";
         }
         else if(s.equals("cloth")){
        	  objectName="HandTowel";
         }
         else if(s.equals("bowl")){
        	 objectName="Bowl";
         }
         else if(s.equals("milk")){
        	 //objectName="Container";
        	  objectName="FoodJar";
         }
         else if(s.equals("remote")){
        	 objectName="RemoteControl";
         }
         else if(s.equals("plate")){
        	 objectName="Plate";
         }
         else if(s.equals("medcinebox")){
        	 objectName="CerealBox";
         }
         else if(s.equals("cup")){
        	 objectName="Cup";
         }
        
        String finalClassExpression="";
        String queryResult[]=new String[11];
        while(countQuery<10){
        	//System.out.println("class expression in Manchester Syntax :");
        	String classExpression;
        	switch (countQuery) {
	        	case 0: classExpression = "inverse (hasVolume) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break;
	        	case 1: classExpression = "inverse (hasWeight) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break;
	        	case 2: classExpression = "inverse (hasGeometricShape) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break;
	        	case 3: classExpression = "inverse (hasConcavity) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break;
	        	case 4: classExpression = "inverse (hasBaseConcavity) value ";
		 		finalClassExpression=classExpression+objectName;
		 		break;
	        	case 5: classExpression = "inverse (hasImmovablePart) value ";
				finalClassExpression=classExpression+objectName;
				break;
	        	case 6: classExpression = "inverse (hasDetachablePart) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break; 
	        	case 7: classExpression = "inverse (hasNonDetachablePart) value ";
	        		finalClassExpression=classExpression+objectName;
	        		break; 
	        	case 8: classExpression = "inverse (hasPart) value ";
		 		finalClassExpression=classExpression+objectName;
		 		break;
	        	case 9:  //classExpression = "{Apple}";
	        		classExpression = "{"+objectName+"}";
	        		finalClassExpression=classExpression;
	        		break;
	                default: finalClassExpression = "Invalid query";
	                        break;
                }
        	//System.out.println("Executed Query :" + finalClassExpression);
        	System.out.println();
        	queryResult[countQuery]=DLQPrinter.askQuery(finalClassExpression.trim(), countQuery);
            	System.out.println();
        	countQuery++;
        }
        System.out.println("Successfully Returned the Loaded Array from OntologyProcessing Module");
        System.out.println();
        PrintWriter writer = new PrintWriter("affordance.mln", "UTF-8");
        writer.println(" Volume (object) ="+queryResult[1]+"^ Weight(object)="+queryResult[2]);
        //writer.println("The second line");
        writer.close();
        return queryResult;
 } //End of method getObjectProperty(String s).
	
	class DLQEngine {
		private final OWLReasoner reasoner;
	    	private final DLQParser parser;

	    	public DLQEngine(OWLReasoner reasoner, ShortFormProvider shortFormProvider) {
	    		this.reasoner = reasoner;
	    		parser = new DLQParser(reasoner.getRootOntology(), shortFormProvider);
	    	}

	    	public Set<OWLClass> getSuperClasses(String classExpressionString, boolean direct) {
	    		if (classExpressionString.trim().length() == 0) {
	    			return Collections.emptySet();
	    		}
	    		OWLClassExpression classExpression = parser
	    				.parseClassExpression(classExpressionString);
	    		NodeSet<OWLClass> superClasses = reasoner
	    				.getSuperClasses(classExpression, direct);
	    		return superClasses.getFlattened();
	    	}

	    	public Set<OWLClass> getEquivalentClasses(String classExpressionString) {
	    			if (classExpressionString.trim().length() == 0) {
	    				return Collections.emptySet();
	    			}
	    			OWLClassExpression classExpression = parser
	    					.parseClassExpression(classExpressionString);
	    			Node<OWLClass> equivalentClasses = reasoner.getEquivalentClasses(classExpression);
	    				Set<OWLClass> result = null;
	    			if (classExpression.isAnonymous()) {
	    				result = equivalentClasses.getEntities();
	    			} 
	    			else {
	    				result = equivalentClasses.getEntitiesMinus(classExpression.asOWLClass());
	    			}
	    			return result;
	        }

	    	public Set<OWLClass> getSubClasses(String classExpressionString, boolean direct) {
	    			if (classExpressionString.trim().length() == 0) {
	    				return Collections.emptySet();
	    			}
	    			OWLClassExpression classExpression = parser
	    					.parseClassExpression(classExpressionString);
	    			NodeSet<OWLClass> subClasses = reasoner.getSubClasses(classExpression, direct);
	    			return subClasses.getFlattened();
	        }
	    /*
	     * @Functionality : Get the direct super classes similar to protege.
	     */
	    	public Set<OWLClass> getDirectSuperClasses(String classExpressionString, boolean direct) {
	    		if (classExpressionString.trim().length() == 0) {
	    			return Collections.emptySet();
	    		}
	    		OWLClassExpression classExpression = parser
	    				.parseClassExpression(classExpressionString);
	    		NodeSet<OWLClass> subClasses = reasoner.getSubClasses(classExpression, direct);
	    		//NodeSet<OWLClass> subClasses = reasoner.get
	    		return subClasses.getFlattened();
	        }

	    	public Set<OWLNamedIndividual> getInstances(String classExpressionString,
	    		boolean direct) {
	    		if (classExpressionString.trim().length() == 0) {
	    			return Collections.emptySet();
	        }
	    		OWLClassExpression classExpression = parser
	    				.parseClassExpression(classExpressionString);
	    		NodeSet<OWLNamedIndividual> individuals = reasoner.getInstances(classExpression,
	    				direct);
	    		return individuals.getFlattened();
	        }
	    }// End of DLQEngine Class

	class DLQParser {
	    	private final OWLOntology rootOntology;
	    	private final BidirectionalShortFormProvider bidiShortFormProvider;

	    	public DLQParser(OWLOntology rootOntology, ShortFormProvider shortFormProvider) {
	    		this.rootOntology = rootOntology;
	    		OWLOntologyManager manager = rootOntology.getOWLOntologyManager();
	    		Set<OWLOntology> importsClosure = rootOntology.getImportsClosure();
	    		// Create a bidirectional short form provider to do the actual mapping.
	    		// It will generate names using the input
	    		// short form provider.
	    		bidiShortFormProvider = new BidirectionalShortFormProviderAdapter(manager,
	    				importsClosure, shortFormProvider);
	    	}

	    	public OWLClassExpression parseClassExpression(String classExpressionString) {
	    		OWLDataFactory dataFactory = rootOntology.getOWLOntologyManager()
	    				.getOWLDataFactory();
	    		ManchesterOWLSyntaxEditorParser parser = new ManchesterOWLSyntaxEditorParser(
	    				dataFactory, classExpressionString);
	    		parser.setDefaultOntology(rootOntology);
	    		OWLEntityChecker entityChecker = new ShortFormEntityChecker(bidiShortFormProvider);
	    			parser.setOWLEntityChecker(entityChecker);
	    		try {
	    			return parser.parseClassExpression();
	    		} catch (ParserException e) {
	    			e.printStackTrace();
	    		}
	    		return null;
	        }
	    } // End of DLQParser Class  

	class DLQPrinter {
	    	private final DLQEngine DLQEngine;
	    	private final ShortFormProvider shortFormProvider;

	    	public DLQPrinter(DLQEngine engine, ShortFormProvider shortFormProvider) {
	    		this.shortFormProvider = shortFormProvider;
	    		DLQEngine = engine;
	        }

	    	public String askQuery(String classExpression, int counter) {
	    		String[] objArr=new String[11] ;
	    		if (classExpression.length() == 0) {
	    			System.out.println("No class expression specified");
	    		} 
	    		else {
	    			StringBuilder sb = new StringBuilder();
	    			Set<OWLNamedIndividual> individuals = DLQEngine.getInstances(
	    					classExpression, true);
	    			printMyEntities("Instances", individuals, sb);
	    			//System.out.println(sb.toString());
	    			objArr[counter]=sb.toString();
	    			//System.out.println("objArr["+counter+"]"+" contains-->"+objArr[counter]);
	    			System.out.println("******************");
	            }
	        	return objArr[counter];
	        }
	    	public void getInstanceInfo(String classExpression) {
	    		if (classExpression.length() == 0) {
	    			System.out.println("No class expression specified");
	    		} else {
	    			StringBuilder sb = new StringBuilder();
	    			//sb.append("\\nQUERY:   ").append(classExpression).append("\\n\\n");
	    			Set<OWLNamedIndividual> individuals = DLQEngine.getInstances(
	    					classExpression, true);
	    			//printEntities("Instances", individuals, sb);
	    			printMyEntities("Instances", individuals, sb);
	    			//System.out.println(sb.toString());
				}
	        }
	    	private void printEntities(String name, Set<? extends OWLEntity> entities,
	    			StringBuilder sb) {
	    			sb.append(name);
	    			int length = 50 - name.length();
	    			for (int i = 0; i < length; i++) {
	    				sb.append(".");
	    			}
	    			sb.append("\n\n");
	    			if (!entities.isEmpty()) {
	    				for (OWLEntity entity : entities) {
	    					//sb.append("\t").append(shortFormProvider.getShortForm(entity))
	                        //.append("\n");
	    					sb.append(shortFormProvider.getShortForm(entity));
	    				}
	    			} else {
	    				//sb.append("\t[NONE]\n");
	    				sb.append("[NONE]");
	    			}
	    				sb.append("\n");
	    	}
	    	private void printMyEntities(String name, Set<? extends OWLEntity> entities,
	    			StringBuilder sb) {
	    		if (!entities.isEmpty()) {
	    			for (OWLEntity entity : entities) {
	    				//sb.append("\t").append(shortFormProvider.getShortForm(entity))
	                      //  .append("\n");
	    				sb.append(shortFormProvider.getShortForm(entity));
	    			}
	    		} else {
	    			//sb.append("\t[NONE]\n");
	    			//sb.append("\t[NONE]\n");
	    			sb.append("[NONE]");
	            }
	    	}
		}// End of DLQPrinter Class  
}// End of OntologyProcessingModule.java
