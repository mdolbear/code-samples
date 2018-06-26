package com.cracking.projectbuildorder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;





/**
 *
 *
 */
public class Project<T> {

    private T data;
    private List<Project<T>> children;
    private boolean visited;
    
    /**
     * Answer an instance for the following arguments
     * @param aData T
     */
    public Project(T aData) {

        super();
        this.setData(aData);
        this.setChildren(new ArrayList<Project<T>>());
        this.setVisited(false);
    }

    /**
     * Answer my data
     * @return T
     */
    protected T getData() {
        return data;
    }

    /**
     * Set my data
     * @param data T
     */
    protected void setData(T data) {
        this.data = data;
    }

    /**
     * Add aChild to me
     * @param aChild Project<T>
     */
    public void addChild(Project<T> aChild) {
        
        this.getChildren().add(aChild);
        
    }
    
    /**
     * Answer my children
     * @return List<Project<T>>
     */
    public List<Project<T>> getChildren() {
        return children;
    }

    /**
     * Set my children
     * @param children List<Project<T>>
     */
    protected void setChildren(List<Project<T>> children) {
        this.children = children;
    }

    /**
     * Answer my visited
     * @return boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Set my visited
     * @param visited boolean
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    
    /**
     * Answer my string representation
     * @return String
     */
    public String toString() {
        
        StringBuilder   tempBuilder = new StringBuilder();
        
        tempBuilder.append("data: ");
        tempBuilder.append((this.getData() != null) ? this.getData().toString() : "null");
        
        return tempBuilder.toString();
        
    }
    
    /**
     * Answer whether or not I have aData
     * @param aData T
     * @return boolean
     */
    public boolean hasData(T aData) {
        
        return this.getData() != null
                && this.getData().equals(aData);
        
    }
    
    /**
     * Answer the Project that has aData. Either myself or one of my ancestors
     * @param aData T
     * @return Project<T>
     */
    public Project<T> getProjectWithData(T aData) {
        
        Project<T>           tempResult = null;
        Project<T>           tempCurrent;
        Iterator<Project<T>> tempChildren;
        
        if (this.hasData(aData))  {
            
            tempResult = this;
        }
        else {
            
            tempChildren = this.getChildren().iterator();
            while (tempResult == null && tempChildren.hasNext()) {
                
                tempCurrent = tempChildren.next();
                tempResult = tempCurrent.getProjectWithData(aData);
            }
            
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not a Project is a dependent project of me
     * @param aProject Project<T>
     * @return boolean
     */
    public boolean isDependent(Project<T> aProject) {
        
        boolean                 tempResult = false;
        Iterator<Project<T>>    tempChildProjects;
        Project<T>              tempCurrent;
        
        tempChildProjects = this.getChildren().iterator();
        while (!tempResult && tempChildProjects.hasNext()) {
            
            tempCurrent = tempChildProjects.next();
            tempResult = tempCurrent.hasData(aProject.getData());
            if (!tempResult) {
                
                tempResult = tempCurrent.isDependent(aProject);
            }
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer my hash code
     * @return int
     */
    @Override
    public int hashCode() {
        
        int tempValue = 0;
        
        if (this.getData() != null) {
            
            tempValue = this.getData().hashCode();
        }
        
        return tempValue;
        
    }
    
    /**
     * Answer whether or not I am equal to anotherProject
     * @param anotherProject Project<T>
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object anotherProject) {
        
        boolean         tempResult = false;
        Project<T>      tempAnotherProject;
        
        if (anotherProject instanceof Project) {
            
            tempAnotherProject = (Project<T>)anotherProject;
            tempResult = (this.getData() == null &&
                                tempAnotherProject.getData() == null) 
                        ||
                   (this.getData() != null &&
                            tempAnotherProject.getData() != null &&
                                this.getData().equals(tempAnotherProject.getData()));
        }
        
        return tempResult;
        
    }
    
    /**
     * Answer whether or not I have a cycle
     * @param aStack Stack<T>
     * @return boolean
     */
    public boolean hasCycle(Stack<T> aPreviousDatas) {
        
        boolean                     tempResult = false;
        Iterator<Project<T>>        tempChildProjects;
        Project<T>                  tempCurrent;
        
        tempResult = aPreviousDatas.contains(this.getData());
        if (!tempResult &&
                !this.isVisited()) {
            
            aPreviousDatas.push(this.getData());
            this.setVisited(true);
            
            tempChildProjects = this.getChildren().iterator();
            while (!tempResult && tempChildProjects.hasNext()) {
                
                tempCurrent = tempChildProjects.next();
                tempResult = tempCurrent.hasCycle(aPreviousDatas);
                
            }
            
        }
        
        
        
        
        return tempResult;
    }
    
    /**
     * Clear visited history. This will only clear nodes that have already been visited.
     */
     protected void clearVistedHistory() {
         

         this.setVisited(false);
         
         for (Project<T> aChildProject: this.getChildren())  {
             
             //Dives deep on all visited children first
             if (aChildProject.isVisited()) {
                 
                 aChildProject.clearVistedHistory();
             }
         }
             

         
         
     }
    
}
