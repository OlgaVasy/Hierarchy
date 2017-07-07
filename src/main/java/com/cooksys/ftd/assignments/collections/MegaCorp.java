package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {	

	HashSet<Capitalist>set = new HashSet<>();
	HashSet<FatCat>cat = new HashSet<>();
	HashMap<FatCat, Set<Capitalist>>map = new HashMap<>();   

	/**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */

    @Override
    public boolean add(Capitalist capitalist) {        
    	
    	boolean addition = false;
    	
    	if (capitalist!=null){
    		
    	
    		if (set.contains(capitalist)) addition=false;
    	
    		if (!capitalist.hasParent() && capitalist instanceof FatCat){
    			set.add(capitalist);
    			cat.add( (FatCat) capitalist );
    			addition = true;

        	
    		} else if(capitalist.hasParent()){
    			this.add(capitalist.getParent());
    			cat.add(capitalist.getParent());
    			set.add(capitalist);
    			addition = true;
    		}
//    		if (capitalist.hasParent() && !set.contains(capitalist.getParent())){
//    			set.add(capitalist.getParent());  
//    			addition=true;
//    			if (!set.contains(capitalist)) set.add(capitalist);
//    			cat.add(capitalist.getParent());
//    			addition=true;
//    		}
    	
    	}
    	return addition;
    	
    	
    }    	
    	
    	
    	  	

  
    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {      	
    	
//    	if (add(capitalist))
//    		return true;
//    	
//    	else 
    	if (set.contains(capitalist))
    		return true;
    	else return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() { 
    
    	return new HashSet<Capitalist>(set); 
    	
    	
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
  //  @Override
    public Set<FatCat> getParents() {
   
    	return new HashSet<FatCat>(cat);
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	
    	HashSet<Capitalist>children = new HashSet<>();
    	
    	//if (add(fatCat))
    	
    	for (Iterator<Capitalist> it = set.iterator(); it.hasNext(); ) {
            Capitalist capitalist = it.next();
            if (capitalist.getParent()==fatCat)
                children.add(capitalist);
          
    }
    	return new HashSet<Capitalist>(children);}

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
        
    	//HashMap<FatCat, Set<Capitalist>>map = new HashMap<>();    	
    	
    	for (Iterator<FatCat> it = cat.iterator(); it.hasNext(); ) {
            FatCat fatcat = it.next();            
            if (getChildren(fatcat)!=null)
            	map.put(fatcat,getChildren(fatcat));
            else
            	map.put(fatcat, new HashSet<>());
    	}
    	
    	
    	return new HashMap<FatCat, Set<Capitalist>>(map);
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
        
    	ArrayList<FatCat>list = new ArrayList<>();     	
    	if(capitalist==null)
    		return list;
    	while(capitalist.hasParent()){
    		list.add(capitalist.getParent());
    		capitalist = list.get(list.size()-1);
    	}
    	  	
    	return list;

    }
}
