package org.bill.RefactoringToGuava;


import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pairing {

    private static final int COMPARE_FLIGHT_DUTY = 0;
    private static final int COMPARE_WORK_BLOCK = 1;

    public Pairing findLeastRestBetweenPairings(Line line, int ruleRest, int compareMethod) {  //MB 9/2012 Remove brief & debrief.  Previously set to 0.
        Pairing aPair = null;
        List<Pairing> pairingArray = createPairingRuleArray(line);
        List<Pair<Pairing, Pairing>> pairingPairs = pairUpNeighbors(pairingArray);
        List<LinePairing> linePairingArray = createLinePairingRuleArray(line);
        List<Pair<LinePairing, LinePairing>> linePairingPairs = pairUpNeighbors(linePairingArray);
        Integer leastRest = null;
        Iterator<Pair<LinePairing,LinePairing>> linePairingPairIterator = linePairingPairs.iterator();
        boolean debugOn = isDebugOn(line);
        for (Pair<Pairing, Pairing> pairingPair : pairingPairs) {
            Pair<LinePairing, LinePairing> linePairingPair = linePairingPairIterator.next();
            switch (compareMethod) {
                case COMPARE_FLIGHT_DUTY:
                    aPair = getLinePairingRestTimeByDutyBetweenPairings(pairingPair, linePairingPair, ruleRest, debugOn);
                    if (aPair != null) {
                        return aPair;
                    }
                    break;
                case COMPARE_WORK_BLOCK:
                    Integer rest = getLinePairingRestTimeByWorkBlock(pairingPair, linePairingPair);
                    if ((leastRest == null) && (rest != 0) && !ignoreDaylightSavingsEffect(rest)) {
                        leastRest = rest;
                    }
                    if ((leastRest != null) && (rest < leastRest.intValue()) && (rest != 0) && !ignoreDaylightSavingsEffect(rest)) {
                        leastRest = rest;
                    }
                    if (leastRest != null) {
                        if (leastRest.intValue() < ruleRest) {
                            return pairingPair.fst;
                        }
                    }
                    break;
            }
        }
        return aPair;
    }

    private Integer getLinePairingRestTimeByWorkBlock(Pair<Pairing, Pairing> pairingPair, Pair<LinePairing, LinePairing> linePairingPair) {
        return null;
    }

    private Pairing getLinePairingRestTimeByDutyBetweenPairings(Pair<Pairing, Pairing> pairingPair, Pair<LinePairing, LinePairing> linePairingPair, int ruleRest, boolean debugOn) {
        return null;
    }

    private <T> List<Pair<T, T>> pairUpNeighbors(List<T> array) {
        return null;
    }

    private boolean isDebugOn(Line line) {
        boolean debugOn = false;
        if (line.getLineSolutionID() == 71493) {
            if (line.getBidLineNumber() == 17) {
                debugOn = true;
            } else {
                debugOn = false;
            }
        }
        return debugOn;
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
}


