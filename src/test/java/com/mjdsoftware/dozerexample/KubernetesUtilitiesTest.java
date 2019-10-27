package com.mjdsoftware.dozerexample;

import com.mjdsoftware.KubernetesUtilities;
import io.kubernetes.client.ApiException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class KubernetesUtilitiesTest {

    /**
     * Basic test
     */
    @Test
    @Ignore
    public void basicTest() throws Exception {

        List<String> tempNamespaces;
        List<String> tempServices;
        String       tempLog;
        String       tempFirstPodname;

        //Create utilities instance
        KubernetesUtilities tempUtils = new KubernetesUtilities(false);
        tempUtils.initializeClient();


        // ScaleUp/ScaleDown the Deployment pod
        // Please change the name of Deployment?
        System.out.println("----- Scale Deployment Start -----");
        tempUtils.scaleDeployment(KubernetesUtilities.DEFAULT_NAME_SPACE,
                  "blackjack",
                  5);

        // List all of the namaspaces and pods
        tempNamespaces = tempUtils.asNamespaceNames(tempUtils.getAllNameSpaces());
        assertTrue("No namespaces present", tempNamespaces != null
                                                                && !tempNamespaces.isEmpty());
        this.dumpNamespaceNames(tempNamespaces, tempUtils);

        // Print all of the Services
        tempServices =
                tempUtils.asServiceNames(tempUtils.getServices(KubernetesUtilities.DEFAULT_NAME_SPACE));
        assertTrue("No service names present", tempServices != null &&
                                                                    !tempServices.isEmpty());
        this.dumpServiceNames(tempServices);

        // Print log of specific pod. In this example show the first pod logs.
        tempFirstPodname = tempUtils.asPodNames(tempUtils.getPods()).get(0);
        tempLog = tempUtils.readLogForPod(KubernetesUtilities.DEFAULT_NAME_SPACE, tempFirstPodname);
        assertTrue("Missing first pod name", tempFirstPodname != null);
        assertTrue("Log missing", tempLog != null);

        this.dumpLog(tempFirstPodname, tempLog);


    }

    /**
     * Dump log contents for pod
     * @param aFirstPodName String
     * @param aLogContents String
     *
     */
    private void dumpLog(String aFirstPodName, String aLogContents)  {

        System.out.println("----- Log for pod " + aFirstPodName + " Start -----");
        System.out.println(aLogContents);
        System.out.println("----- Print Log of Specific Pod End -----");

    }

    /**
     * Dump service names
     * @param aServices List
     */
    private void dumpServiceNames(List<String> aServices) {

        System.out.println("----- Print list all Services Start -----");
        aServices.stream().forEach(System.out::println);
        System.out.println("----- Print list all Services End -----");

    }

    /**
     * Dump namespace names
     * @param aNamespaces List
     * @param aUtils KubernetesUtilities
     */
    private void dumpNamespaceNames(List<String> aNamespaces,
                                    KubernetesUtilities aUtils) {

        aNamespaces
                .forEach(
                        namespace -> {
                            try {
                                System.out.println("----- " + namespace + " -----");
                                aUtils.getNamespacedPod(namespace).forEach(System.out::println);
                            } catch (ApiException ex) {
                                System.out.println("Couldn't get the pods in namespace:" + namespace);
                                ex.printStackTrace();
                            }

                        });

    }

}
