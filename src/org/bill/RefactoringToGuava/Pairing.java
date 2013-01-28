package org.bill.RefactoringToGuava;


import java.util.ArrayList;

public class Pairing {

    private static final int COMPARE_FLIGHT_DUTY = 0;
    private static final int COMPARE_WORK_BLOCK = 1;

    public Pairing findLeastRestBetweenPairings(Line line, int ruleRest, int comparemethod) {  //MB 9/2012 Remove brief & debrief.  Previously set to 0.
        Pairing aPair = null;
        ArrayList<Pairing> pairingarray = createPairingRuleArray(line);
        ArrayList<LinePairing> linepairingarray = createLinePairingRuleArray(line);
        Pairing pairing1 = null;
        Pairing pairing2 = null;
        LinePairing linepairing1;
        LinePairing linepairing2;
        int rest = 0;
        Integer leastrest = null;
        boolean debugOn = false;
        if (line.getLineSolutionID() == 71493) {
            if (line.getBidLineNumber() == 17) {
                debugOn = true;
            } else {
                debugOn = false;
            }
        }
        for (int n = 1; n < pairingarray.size(); n++) {
            pairing1 = pairingarray.get((n - 1));
            pairing2 = pairingarray.get(n);
            linepairing1 = linepairingarray.get(n - 1);
            linepairing2 = linepairingarray.get(n);
            switch (comparemethod) {
                case COMPARE_FLIGHT_DUTY:
                    aPair = getLinePairingRestTimeByDutyBetweenPairings(pairing1, linepairing1, pairing2, linepairing2, ruleRest, debugOn);
                    if (aPair != null) {
                        return aPair;
                    }
                    break;
                case COMPARE_WORK_BLOCK:
                    rest = getLinePairingRestTimeByWorkBlock(pairing1, linepairing1, pairing2, linepairing2);
                    if ((leastrest == null) && (rest != 0) && !ignoreDaylightSavingsEffect(rest)) {
                        leastrest = new Integer(rest);
                    }
                    if ((leastrest != null) && (rest < leastrest.intValue()) && (rest != 0) && !ignoreDaylightSavingsEffect(rest)) {
                        leastrest = new Integer(rest);
                    }
                    if (leastrest != null) {
                        if (leastrest.intValue() < ruleRest) {
                            return pairing1;
                        }
                    }
                    break;
            }
        }
        return aPair;
    }

    private boolean ignoreDaylightSavingsEffect(int rest) {
        return false;
    }

    private ArrayList<Pairing> createPairingRuleArray(Line line){
        return null;
    }

    private ArrayList<LinePairing> createLinePairingRuleArray(Line line){
        return null;
    }

    private Pairing getLinePairingRestTimeByDutyBetweenPairings(
            Pairing pairing1, LinePairing linepairing1,
            Pairing pairing2, LinePairing linepairing2,
            int ruleRest, boolean debugOn){
        return null;
    }

    private int getLinePairingRestTimeByWorkBlock(Pairing pairing1, LinePairing linepairing1,
                                                  Pairing pairing2, LinePairing linepairing2){
        return 0;
    }
}


