package com.cracking.projectbuildorder;

/**
 *
 *
 */
public class ProjectDependency<T> {

    private T rootProject;
    private T dependentProject;
    
    /**
     * Answer an instance for the following arguments
     * @param aRootProject T
     * @param aDependentProject T
     */
    public ProjectDependency(T aRootProject,
                             T aDependentProject) {
        
        super();
        this.setRootProject(aRootProject);
        this.setDependentProject(aDependentProject);
        
    }

    /**
     * Answer my rootProject
     * @return T
     */
    public T getRootProject() {
        return rootProject;
    }

    /**
     * Set my rootProject
     * @param rootProject T
     */
    protected void setRootProject(T rootProject) {
        this.rootProject = rootProject;
    }

    /**
     * Answer my dependentProject
     * @return T
     */
    public T getDependentProject() {
        return dependentProject;
    }

    /**
     * Set my dependentProject
     * @param dependentProject T
     */
    protected void setDependentProject(T dependentProject) {
        this.dependentProject = dependentProject;
    }
    
    

}
