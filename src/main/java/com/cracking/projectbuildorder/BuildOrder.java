package com.cracking.projectbuildorder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;






/**
 *
 *
 */
public class BuildOrder<T> {

    private List<Project<T>> projects;
    
    /**
     * Answer an instance for the following arguments
     */
    public BuildOrder() {
       
        super();
        this.setProjects(new ArrayList<Project<T>>());
    }

    
    /**
     * Add aProject to me
     * @param aProject Project<T>
     */
    public void addProject(Project<T> aProject) {
        
        this.getProjects().add(aProject);
        
    }
    
    
    /**
     * Answer my projects
     * @return List<Project<T>>
     */
    protected List<Project<T>> getProjects() {
        return projects;
    }

    /**
     * Set my projects
     * @param projects List<Project<T>>
     */
    protected void setProjects(List<Project<T>> projects) {
        this.projects = projects;
    }

    
    /**
     * Perform topological sort. This is for a directed graph
     * @return Stack
     */
    public Stack<Project<T>> performTopologicalSort() 
                            throws IllegalStateException {
        
        Stack<Project<T>>          tempResult = new Stack<Project<T>>();
        List<Project<T>>           tempProjects;
        
        tempProjects = new ArrayList<Project<T>>(this.getProjects());
        
        //Clear visited history
        this.clearVisitedHistory(tempProjects);
        
        //perform sort
        for (int i = 0; i < this.getProjects().size(); i++) {
            
            if (!tempProjects.get(i).isVisited()) {
                
                this.basicPeformTopolocialSort(tempProjects.get(i),
                                               tempResult);
                
            }
            
        }
        
        
        return tempResult;
        
    }


    /**
     * Clear visited history
     * @param aProjects
     */
    protected void clearVisitedHistory(List<Project<T>> aProjects) {
        
        for (Project<T> aProject: aProjects) {
            
            aProject.clearVistedHistory();
        }
        
    }

    /**
     * Recursively perform topological sort
     * 
     * @param aProject
     * @param aVisited
     * @param aStack
     */
    protected void basicPeformTopolocialSort(Project<T> aProject,
                                            Stack<Project<T>> aStack) {
      
        
        Iterator<Project<T>>    tempAdjacentProjects;
        Project<T>              tempCurrent;
        
        //Mark Project as visited
        aProject.setVisited(true);
        
        //Perform sort
        tempAdjacentProjects = aProject.getChildren().iterator();
        while (tempAdjacentProjects.hasNext()) {
            
            tempCurrent = tempAdjacentProjects.next();
            if (!tempCurrent.isVisited()) {
                
                this.basicPeformTopolocialSort(tempCurrent, aStack);
            }
        }
        
        aStack.push(aProject);
        
    }
    
    /**
     * Answer the Project that has aData or null
     * @param aData T
     * @return Project<T>
     */
    public Project<T> getProjectWithData(T aData) {
        
        Project<T>           tempResult = null;
        Project<T>           tempCurrent;
        Iterator<Project<T>> tempProjects;
        
        tempProjects = this.getProjects().iterator();
        while (tempResult == null &&
                tempProjects.hasNext()) {
            
            tempCurrent = tempProjects.next();
            tempResult = tempCurrent.getProjectWithData(aData);
        }
        
        return tempResult;
        
    }
    
    
    
    /**
     * Create initial build order
     * @param aProjectDependencyDatas List<T>
     * @param aProjectDependencyDatas List<ProjectDependency<T>>
     */
    public void createInitialBuildOrder(List<T> aProjectDatas,
                                        List<ProjectDependency<T>> aProjectDependencyDatas) {
        
        Set<Project<T>>             tempProjects = new HashSet<Project<T>>();
        
        this.createProjects(aProjectDatas, tempProjects);        
        this.associateProjectsUsingDependencies(aProjectDependencyDatas,
                                                tempProjects);
        this.validateNoCycles(tempProjects);
        this.findAndAddRootProjectsToMe(tempProjects);
        
    }

    /**
     * Validate that there are no cycles in my project dependencies
     * @param tempProjects Set<Project<T>>
     */
    protected void validateNoCycles(Set<Project<T>> tempProjects) {
        
        boolean     tempCycle;
        Stack<T>    tempStack;
        
        this.clearVisitedProjectHistory(tempProjects);
        
        for (Project<T> aProject: tempProjects) {
            
            tempStack = new Stack<T>();
            tempCycle = aProject.hasCycle(tempStack);
            if (tempCycle) {
                
                throw new IllegalStateException("Project has a cycle: " + aProject.toString() + " trace stack: " + tempStack.toString());
            }
        }
        
    }


    /**
     * Clear visited project history
     * @param aProjects
     */
    protected void clearVisitedProjectHistory(Set<Project<T>> aProjects) {
        for (Project<T> aProject: aProjects) {
            
            aProject.clearVistedHistory();
        }
    }


    /**
     * Associate projects based on the dependency list
     * @param aProjectDependencyDatas
     * @param aProjects
     */
    protected void associateProjectsUsingDependencies(List<ProjectDependency<T>> aProjectDependencyDatas,
                                                      Set<Project<T>> aProjects) {
        
        Project<T> tempRootProject;
        
        for (ProjectDependency<T> aProjectDependency: aProjectDependencyDatas) {
            
            tempRootProject = this.findRootProject(aProjectDependency,
                                                   aProjects);
            this.findDependentProjectAndCreateDependency(tempRootProject, 
                                                         aProjectDependency,
                                                         aProjects);
           
        }
        
    }


    /**
     * Create projects
     * @param aProjectDatas
     * @param aProjects
     */
    protected void createProjects(List<T> aProjectDatas,
                                  Set<Project<T>> aProjects) {
        
        for (T aData: aProjectDatas) {
            
            aProjects.add(new Project<T>(aData));
        }
        
    }


    /**
     * Find and add root projects to me
     * @param aRootProjects
     */
    protected void findAndAddRootProjectsToMe(Set<Project<T>> aRootProjects) {
        
        boolean         tempIsRoot;
        Set<Project<T>> otherProjectSet;
        
        for (Project<T> aPotentialRootProject: aRootProjects) {
            
            otherProjectSet =  new HashSet<Project<T>>(aRootProjects);
            otherProjectSet.remove(aPotentialRootProject);
            
            tempIsRoot = this.isRootProject(aPotentialRootProject, otherProjectSet);
            if (tempIsRoot) {
                
                this.addProject(aPotentialRootProject);
            }
            
        }
        
    }
    
    /**
     * Answer whether or not aPotentialRootProject is a dependent of any of aPotentialRootProjects
     * @param aPotentialRootProject Project<T>
     * @param aPotentialRootProjects Set<Project<T>>
     * @return boolean
     */
    protected boolean isRootProject(Project<T> aPotentialRootProject,
                                    Set<Project<T>> aPotentialRootProjects) {
        
        boolean                 tempResult = false;
        Iterator<Project<T>>    tempChildProjects;
        Project<T>              tempCurrent;       
     
        tempChildProjects = aPotentialRootProjects.iterator();
        while (!tempResult && tempChildProjects.hasNext()) {
            
            tempCurrent = tempChildProjects.next();
            tempResult = tempCurrent.isDependent(aPotentialRootProject);
        }
        
        return !tempResult;
        
    }


    /**
     * Create dependent project. Always add it as a dependent of the root whether or not its newly created
     * @param aRootProject
     * @param aProjectDependency
     * @param aProject List<Project<T>>
     * @return Project<T>
     */
    protected Project<T> findDependentProjectAndCreateDependency(Project<T> aRootProject,
                                                                 ProjectDependency<T> aProjectDependency,
                                                                 Set<Project<T>> aProjects) {
        
        Project<T>               tempDependentProject;
        
        
        tempDependentProject = this.findProjectWithData(aProjects,
                                                        aProjectDependency.getDependentProject());
        aProjects.add(tempDependentProject);
        
        aRootProject.addChild(tempDependentProject);
        
        return tempDependentProject;
    }


    /**
     * Find root project from dependency
     * @param aProjectDependency
     * @param aProjects List<Project<T>>
     * @return Project<T>
     */
    protected Project<T> findRootProject(ProjectDependency<T> aProjectDependency,
                                         Set<Project<T>> aProjects) {
        
        Project<T>               tempRootProject;
        
        tempRootProject = this.findProjectWithData(aProjects,
                                                   aProjectDependency.getRootProject());
        aProjects.add(tempRootProject);
        
        return tempRootProject;
        
    }
    
    /**
     * Find a Project with aData T. Throw exception if it does not exist
     * @param aProjects List<Project<T>>
     * @param aData T
     * @return Project<T>
     */
    protected Project<T> findProjectWithData(Set<Project<T>> aProjects,
                                             T aData) {
        
        Project<T>                    tempProject;
        
        tempProject = this.getProjectWithData(aProjects, aData);
        this.validateProjectNotNull(aData, tempProject);

        return tempProject;
        
    }


    /**
     * Throw exception if project is null
     * @param aData
     * @param aProject
     */
    protected void validateProjectNotNull(T aData, Project<T> aProject) {
        if (aProject == null) {
            
            throw new IllegalArgumentException("Project not found for data: " + aData.toString());
        }
        
    }

    /**
     * Answer a project for aData or null
     * @param aProjects List<Project<T>>
     * @param aData T
     * @return Project<T>
     */
    protected Project<T> getProjectWithData(Set<Project<T>> aProjects, T aData) {
        
        Iterator<Project<T>>    tempItr;
        Project<T>              tempCurrent;
        Project<T>              tempResult = null;
        
        tempItr = aProjects.iterator();
        while (tempResult == null &&
                    tempItr.hasNext()) {
            
            tempCurrent = tempItr.next();
            if (tempCurrent.hasData(aData)) {
                
                tempResult = tempCurrent;
            }
            
        }
        
        return tempResult;
        
    }
    
}
