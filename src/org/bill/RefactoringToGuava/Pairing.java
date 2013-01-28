package org.bill.RefactoringToGuava;


import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pairing {

    private static final int COMPARE_FLIGHT_DUTY = 0;
    private static final int COMPARE_WORK_BLOCK = 1;

    public Pairing findLeastRestBetweenPairings(Line line, int ruleRest, int compareMethod) {
        List<Pair<Pairing, Pairing>> pairingPairs = pairingPairs(line);
        Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator = linePairingPairIterator(line);
        switch (compareMethod) {
            case COMPARE_FLIGHT_DUTY:
                return compareFlightDuty(ruleRest, pairingPairs, linePairingPairIterator, isDebugOn(line));
            case COMPARE_WORK_BLOCK:
                return compareWorkBlock(ruleRest, pairingPairs, linePairingPairIterator);
            default:
                return null;
        }
    }

    private Pairing compareWorkBlock(int ruleRest, List<Pair<Pairing, Pairing>> pairingPairs, Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator) {
        Integer leastRest = null;
        for (Pair<Pairing, Pairing> pairingPair : pairingPairs) {
            Pair<LinePairing, LinePairing> linePairingPair = linePairingPairIterator.next();
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
        }
        return null;
    }

    private Pairing compareFlightDuty(int ruleRest, List<Pair<Pairing, Pairing>> pairingPairs, Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator, Boolean isDebugOn) {
        for (Pair<Pairing, Pairing> pairingPair : pairingPairs) {
            Pair<LinePairing, LinePairing> linePairingPair = linePairingPairIterator.next();
            Pairing pair = getLinePairingRestTimeByDutyBetweenPairings(pairingPair, linePairingPair, ruleRest, isDebugOn);
            if (pair != null) {
                return pair;
            }
        }
        return null;
    }

    private List<Pair<Pairing, Pairing>> pairingPairs(Line line) {
        List<Pairing> pairingArray = createPairingRuleArray(line);
        return pairUpNeighbors(pairingArray);
    }

    private Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator(Line line) {
        List<LinePairing> linePairingArray = createLinePairingRuleArray(line);
        List<Pair<LinePairing, LinePairing>> linePairingPairs = pairUpNeighbors(linePairingArray);
        return linePairingPairs.iterator();
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


