package org.bill.RefactoringToGuava;


import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.transform;

public class Pairing {

    private static final int COMPARE_FLIGHT_DUTY = 0;
    private static final int COMPARE_WORK_BLOCK = 1;

    public Pairing findLeastRestBetweenPairings(Line line, int ruleRest, int compareMethod) {
        List<Pair<Pairing, Pairing>> pairingPairs = pairingPairs(line);
        Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator = linePairingPairIterator(line);
        switch (compareMethod) {
            case COMPARE_FLIGHT_DUTY:
                List<Pairing> pairings = transform(pairingPairs, new PairingFrom(linePairingPairIterator, ruleRest, isDebugOn(line)));
                return find(pairings, notNull());
            case COMPARE_WORK_BLOCK:
                List<RestAndPairings> rests = transform(pairingPairs, new RestFromPairings(linePairingPairIterator));
                return find(rests, new RestIsLestThan(ruleRest)).pairings.fst;
            default:
                return null;
        }
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

    private class RestAndPairings {
        public final Pair<Pairing, Pairing> pairings;
        public final Pair<LinePairing, LinePairing> linePairings;
        public final Integer rest;

        private RestAndPairings(Pair<Pairing, Pairing> pairings, Pair<LinePairing, LinePairing> linePairings, Integer rest) {
            this.pairings = pairings;
            this.linePairings = linePairings;
            this.rest = rest;
        }
    }
    private class RestFromPairings implements Function<Pair<Pairing, Pairing>, RestAndPairings> {

        private final Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator;

        public RestFromPairings(Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator) {
            this.linePairingPairIterator = linePairingPairIterator;
        }

        public RestAndPairings apply(Pair<Pairing, Pairing> pairingPair) {
            Pair<LinePairing, LinePairing> linePairingPair = linePairingPairIterator.next();
            return new RestAndPairings(pairingPair, linePairingPair, getLinePairingRestTimeByWorkBlock(pairingPair, linePairingPair));
        }
    }

    private class RestIsLestThan implements Predicate<RestAndPairings> {
        private final int ruleRest;

        public RestIsLestThan(int ruleRest) {
            this.ruleRest = ruleRest;
        }

        public boolean apply(RestAndPairings restAndPairings) {
            Integer rest = restAndPairings.rest;
            return rest != 0 && !ignoreDaylightSavingsEffect(rest) && rest < ruleRest;
        }
    }

    private class PairingFrom implements Function<Pair<Pairing, Pairing>, Pairing> {
        private final Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator;
        private final int ruleRest;
        private final Boolean debugOn;

        public PairingFrom(Iterator<Pair<LinePairing, LinePairing>> linePairingPairIterator, int ruleRest, Boolean debugOn) {
            this.linePairingPairIterator = linePairingPairIterator;
            this.ruleRest = ruleRest;
            this.debugOn = debugOn;
        }

        public Pairing apply(Pair<Pairing, Pairing> pairingPair) {
            Pair<LinePairing, LinePairing> linePairingPair = linePairingPairIterator.next();
            return getLinePairingRestTimeByDutyBetweenPairings(pairingPair, linePairingPair, ruleRest, debugOn);
        }
    }
}


