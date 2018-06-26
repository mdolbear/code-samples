package com.cracking.projectbuildorder;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 *
 */
public class ProjectBuildOrderTest {

    /**
     * Answer an instance for the following arguments
     */
    public ProjectBuildOrderTest() {
        super();
    }
    
    /**
     * Test project build order
     */
    @Test
    public void projectBuildOrderTest() {
        
        List<String>                         tempProjectDatas;
        List<ProjectDependency<String>>      tempDependencies;
        BuildOrder<String>                   tempBuildOrder;
        Stack<Project<String>>               tempResults;
        Project<String>                      tempProjectOnStack;
        
        //Working project
        tempDependencies = this.createProjectDependenciesForWorkingProject();
        tempProjectDatas = this.createProjectDatas();
        
        tempBuildOrder = new BuildOrder<String>();
        tempBuildOrder.createInitialBuildOrder(tempProjectDatas,
                                               tempDependencies);
        
        tempResults = tempBuildOrder.performTopologicalSort();
        System.out.println("Reverse order of project build: " + tempResults.toString());
        assertTrue("Topological sort failed", !tempResults.isEmpty());
        
        tempProjectOnStack = tempResults.pop();
        assertTrue("Incorrect sort order", tempProjectOnStack.hasData("f"));
        
        //Non-Working project 1
        tempDependencies = this.createProjectDependenciesForNonWorkingProject();
        tempProjectDatas = this.createProjectDatas();
        
        tempBuildOrder = new BuildOrder<String>();
        try {
            tempBuildOrder.createInitialBuildOrder(tempProjectDatas,
                                                   tempDependencies);
            fail("Should not get here - Builder order should detect a cycle so cannot be built");
        }
        catch(IllegalStateException e) {
            
            System.out.println("Project correctly detected cycle in project 1");
            System.out.println("Exception message: " + e.getMessage());
        }
        
        //Non-Working project 2
        tempDependencies = this.createProjectDependenciesForNonWorkingProject2();
        tempProjectDatas = this.createProjectDatas();
        
        tempBuildOrder = new BuildOrder<String>();
        try {
            tempBuildOrder.createInitialBuildOrder(tempProjectDatas,
                                                   tempDependencies);
            fail("Should not get here - Builder order should detect a cycle so cannot be built");
        }
        catch(IllegalStateException e) {
            
            System.out.println("Project correctly detected cycle in project 2");
            System.out.println("Exception message: " + e.getMessage());
        }
        
        
    }
    
    
    
    /**
     * Answer the project datas for a working project
     * @return List<String>
     */
    protected List<String> createProjectDatas() {
        
        String[] tempDatas = {"a", "b", "c", "d", "e", "f"};
        
        return Arrays.asList(tempDatas);
    }
    
    
    /**
     * Create project dependencies
     * @return List<ProjectDependency>
     */
    protected List<ProjectDependency<String>> createProjectDependenciesForWorkingProject() {
        
        ProjectDependency<String>           tempDependency;
        List<ProjectDependency<String>>     tempResult = new ArrayList<ProjectDependency<String>>();
        
        tempDependency = new ProjectDependency<String>("a", "d");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("f", "b");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("b", "d");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("f", "a");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("d", "c");
        tempResult.add(tempDependency);
        
        return tempResult;
        
        
    }
    
    /**
     * Create project dependencies
     * @return List<ProjectDependency>
     */
    protected List<ProjectDependency<String>> createProjectDependenciesForNonWorkingProject() {
        
        ProjectDependency<String>           tempDependency;
        List<ProjectDependency<String>>     tempResult = new ArrayList<ProjectDependency<String>>();
        
        tempDependency = new ProjectDependency<String>("a", "d");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("f", "b");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("b", "d");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("f", "a");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("d", "c");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("c", "f");
        tempResult.add(tempDependency);
        
        return tempResult;
        
        
    }
    

    /**
     * Create project dependencies
     * @return List<ProjectDependency>
     */
    protected List<ProjectDependency<String>> createProjectDependenciesForNonWorkingProject2() {
        
        ProjectDependency<String>           tempDependency;
        List<ProjectDependency<String>>     tempResult = new ArrayList<ProjectDependency<String>>();
        
        tempDependency = new ProjectDependency<String>("a", "d");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("d", "c");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("c", "f");
        tempResult.add(tempDependency);
        
        tempDependency = new ProjectDependency<String>("f", "a");
        tempResult.add(tempDependency);
        
        
        
        return tempResult;
        
        
    }
    
    
}
