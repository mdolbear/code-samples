package com.oracle.regex;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegularExpressionExamples {

    public RegularExpressionExamples() {
        // TODO Auto-generated constructor stub
    }

    
    /**
     * Sample test setup for regular expressions..Java metacharacters: <([{\^-=$!|]})?*+.>
     * Avoiding metacharacters...precede with a \
     *                        ----enclose in \Q \E   --quotes
     *                        inside a character class as well?
     */
    @Test
    public void sampleRegularExpressionTest() {
        
        Pattern tempPattern;
        Matcher tempMatcher;
        boolean tempFound = false;
        
        String  tempTargetString = "<h1><h2>public</h2></h1>"; //this.getAnotherUglyTargetString(); // "<h1>Nayeem loves counseling</h1>"; //"<h1>had<h2>public</h2></h1>"; //"<h1>had<h1>public</h1515></h1>";
        String  tempRegex = "\\<([^>]+)\\>(.+)\\</\\1\\>"; //[^<>]
        String  tempOutput;
        String  tempSecondRegex = "(?<=\\>)(.*)(?=</)";
        Pattern tempSecondPattern;
        Matcher tempSecondMatcher;
        String tempEscapedGroup;
        
        tempPattern = Pattern.compile(tempRegex);
        
        System.out.println("Compiled Pattern: " + tempPattern.pattern());
        tempMatcher = tempPattern.matcher(tempTargetString);
        
        while (tempMatcher.find()) {
            
            tempOutput = String.format("I found the text: " +  " %s   starting at index %d and ending at index %d.%n",
                                       tempMatcher.group(0), tempMatcher.start(), tempMatcher.end());
            System.out.println(tempOutput);
            
            tempOutput = tempMatcher.group(0);
            System.out.println("Group 1 of first matcher: " + tempMatcher.group(1));
            
            tempEscapedGroup = this.replaceSpecialRegexChars( tempMatcher.group(1));
            tempSecondRegex = "\\<" + tempEscapedGroup + "\\>([^<>]*)</" + tempEscapedGroup + "\\>";
            tempSecondPattern = Pattern.compile(tempSecondRegex);
            tempSecondMatcher = tempSecondPattern.matcher(tempOutput);
            
            
            while (tempSecondMatcher.find()) {
                
                System.out.println("Second Match: " + tempSecondMatcher.group(0));
                System.out.println("Second Match Capture: " + tempSecondMatcher.group(1));
            }
            
            System.out.println("------------------------------------------------------------");
            System.out.println();

            tempFound = true;
        }
        
        if (!tempFound) {
            
            System.out.println("No match found");
        }
        
    }
    
    /**
     * Escape special regex characters
     * @param aValue String
     * @return String
     */
    protected String replaceSpecialRegexChars(String aValue) {
        
        
       return "\\Q" + aValue + "\\E";
        
    }
    
    /**
     * Answer another ugly target
     * @return String
     */
    public String getAnotherUglyTargetString() {
        
        return "<xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)><ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2><DqcnlvJJa7ZYYrX^M*BI^*A_-mm0 HT4i^syNtujpEE8M@><mpX{FWXe#$d`)QdqT#eBbH=EJOXc@cFhxpJg14#*G0 o-w9G++`GA9U><PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%><WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#><gXzyXZFrpOGaC+M4)kG`d5L*lRW5A#O0i=V023f_iSwqL$_qRMp59PW gf6><GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>YjncQJp=mN8OV9WEviqvJT`YS$WD=^gryB)NWAal</GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>QMewBnIzsV</WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#></PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%>dPcdFnfTMuYyOFExhXoymEYRVptmHjbgR</ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2>wDegwAFAmjapXOejj</xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)>";
    }
    /**
     * Answer an ugly target string
     * @return String
     */
    public String getUglyTargetString() {
        
        return "wRhDGQCG`r N4=cWqp4jF\"#I7#)jofKUYktmjH(\"s2nD4+NxsN)tpCf2U@78MsGNbEgSgR=6`\"y685~I(D-p&T2BnaJNa%S%y9pYMb_9v" +
                "{PlScQ7R=~~xnSkpSd<orcsjzalN#eslhhH2d\"1MD)pzc*$tBG-mmI_*zW><xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)><ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2><DqcnlvJJa7ZYYrX^M*BI^*A_-mm0 " +
                "HT4i^syNtujpEE8M@><mpX{FWXe#$d`)QdqT#eBbH=EJOXc@cFhxpJg14#*G0 o-w9G++`GA9U><PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%><WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#><gXzyXZFrpOGaC+M4)kG`d5L*lRW5A#O0i=V023f_iSwqL$_qRMp59PW gf6><GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>YjncQJp=mN8OV9WEviqvJT`YS$WD=^gryB)NWAal</GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>QMewBnIzsV</WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#></PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%>dPcdFnfTMuYyOFExhXoymEYRVptmHjbgR</ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)" +
                "Vk3R}2>wDegwAFAmjapXOejj</xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)>gEpaCbApodfJVPXVuct<bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C>rulVew89#uyWF}4`T\"xULOZ%1\"5Cu)&x7qD0</bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C><RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV><ofMiPADbv&XoS=rU{r5JRfE+R}DgSHV@%zDk1golMD_IMuJ1U`~~TyD><lCERjrGdOfuVC0\"8W##`L8x+qC=HW><UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_><pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY>nhPeJWSTIkb Gs_~ztKS*b#36E+ThD</pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY></UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_>zBesDtLSpVYfUzgds</lCERjrGdOfuVC0\"8W##`L8x+qC=HW>OjuAcMjbwMLuylplc</RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV>ZuWHgfxmI&l}D@r%q=mSj  Y&paOAZFf=~}I_Eg<CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS>RCKchSzpkdh0oF aNt (%Vu ^ap3K0GP634h_k%1FsO$Wu0n1aeLEPSBHpGpHdWrIrIApQrorxMIBVbyhh</CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS><XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb><RIEb0nii0bO5WV$%n#}TgB*fLl>RIAFwuGoRsU_9PleTRq{FS}fa3)1kRgn(C}ohOYXDqk</RIEb0nii0bO5WV$%n#}TgB*fLl>fQtRVddxdcuYJAikIUG</XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb>WmwMTg1FWlk0G6(mmh-SN\"k&hoKwzfF0PLel$2)aLS5tW22jz=zX9M7S88Hm_1aF7<rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1>tULUXNsa`iS~T(_BQ`EqJNSgxcO)$`~d=`F2Dqbst4-_N</rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1><MB=7g =8{_aUlwE_>MoUMPsYfBJNd$G~\"=dbO0U%aiOUaBBG&%(oFSfOZYvXSPFfwh@R QqyvTQ)3rJu%k6OBuBXkSShV=nm\"qJ46_VKi1- oHK^EdKTk~gs%t\"8<XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>LfOJpqbcGhsTzasaaYU</XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>YYYbEkrZ^wz=4046`&}N (^*_8EO^Eraw*jTFD~+IX%YX_Oo`f3BlFjmhSrEtE8FTe5870&+z(i@czXtI2mRfw7p^yFO_~r{*D0su6w&vnXb0aC+52Kol<byi)o%`Y{NBe>zYcZaQOKUCW4G0mJ$xe*xGIuY-&@1S`wkck%203&P{N}<lHrYNvRe#sQdvy-TurFDw+lUOyM1UI2sv`=><Nrp\"OjKiUXOhM(fRCqS\"C1-SmCTx%urq^=NEF-i*q&x><XKKZIu2zuIXam%aUp`bmJ(JwI`A";
    }

    
    
    /**
     * Sample test setup for regular expressions..Java metacharacters: <([{\^-=$!|]})?*+.>
     * Avoiding metacharacters...precede with a \
     *                        ----enclose in \Q \E   --quotes
     *                        inside a character class as well?
     */
    @Test
    public void sampleLookbackRegularExpressionTest() {
        
        Pattern tempPattern;
        Matcher tempMatcher;
        boolean tempFound = false;
        
        String  tempTargetString = "FLORAL";
        String  tempRegex = "(?<=FL)(\\w+)(?=AL)";
        String  tempOutput;
        
        tempPattern = Pattern.compile(tempRegex);
        System.out.println("Compiled Pattern: " + tempPattern.pattern());
        tempMatcher = tempPattern.matcher(tempTargetString);
        
        while (tempMatcher.find()) {
            
            tempOutput = String.format("I found the text: " +  " %s   starting at index %d and ending at index %d.%n",
                                       tempMatcher.group(0), tempMatcher.start(), tempMatcher.end());
            System.out.println(tempOutput);
            tempFound = true;
        }
        
        if (!tempFound) {
            
            System.out.println("No match found");
        }
        
    }
    
    /**
     * Sample test setup for regular expressions..Java metacharacters: <([{\^-=$!|]})?*+.>
     * Avoiding metacharacters...precede with a \
     *                        ----enclose in \Q \E   --quotes
     *                        inside a character class as well?
     */
    @Test
    public void sampleRegularExpressionWithReplaceTest() {
        
        Pattern tempPattern;
        Matcher tempMatcher;
        boolean tempFound = false;
        StringBuffer    tempBuff = new StringBuffer();
        
        String  tempTargetString = " roo roo roo";
        String  tempRegex = "(?:\\b)([a-z])+[a-zA-Z]*\\S";
        String  tempOutput;
        String  tempReplacement = "boo";
        
        tempPattern = Pattern.compile(tempRegex);
        tempMatcher = tempPattern.matcher(tempTargetString);
        
        while (tempMatcher.find()) {
            
            tempOutput = String.format("I found the text" + " \"%s\" starting at index %d and ending at index %d.%n",
                                       tempMatcher.group(), tempMatcher.start(), tempMatcher.end());
            System.out.println(tempOutput);
            tempMatcher.appendReplacement(tempBuff, tempReplacement);
            tempFound = true;
        }
        
        tempMatcher.appendTail(tempBuff);
        
        System.out.println("Replacement Text: " + tempBuff.toString());
        
        if (!tempFound) {
            
            System.out.println("No match found");
        }
        
    }


    /**
     * Test of not matching certain reserved words
     */
    @Test
    public void notMatchingCertainWordsTest() {

        String  tempTestMatches = "OR you can do this";
        String  tempTestAnotherMatches = "or you can do this";
        String  tempTestNoMatches = "andORto andORto blah blah blah blah blah toorand ortoand blah blah blah";

        assertTrue("Should match this", this.getFirstContainedReservedWord(tempTestMatches)
                                                    != null);

        assertTrue("Should match this", this.getFirstContainedReservedWord(tempTestAnotherMatches)
                                                                != null);


        assertTrue("Should not match this",
                        this.getFirstContainedReservedWord(tempTestNoMatches) == null);
    }


    /**
     * Answer whether aLine contains any reserved words. Answer null if there are none, or
     * the reserved word if it encounters one
     * @param aLine String
     * @return String
     */
    private String getFirstContainedReservedWord(String aLine) {

        Iterator<Pattern>   tempPatterns;
        String              tempFound = null;
        Matcher             tempMatcher;

        tempPatterns = this.getReservedWordPatterns().iterator();
        while (tempPatterns.hasNext() && (tempFound == null)) {

            tempMatcher = tempPatterns.next().matcher(aLine);
            if (tempMatcher.find()) {

                tempFound = tempMatcher.pattern().toString();
            }
        }

        return tempFound;

    }


    /**
     * Answer the patterns needed for reserved words
     * @return List
     */
    private List<Pattern> getReservedWordPatterns() {

        String[]                tempRegexReservedWords = {"\\bOR\\b", "\\bAND\\b", "\\bTO\\b"};
        List<Pattern>           tempPatterns = new ArrayList<Pattern>();


        for (String aReservedWord: tempRegexReservedWords) {

           tempPatterns.add(Pattern.compile(aReservedWord, Pattern.CASE_INSENSITIVE));
        }

        return tempPatterns;

    }

}
