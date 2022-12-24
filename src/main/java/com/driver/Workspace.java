package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar = new ArrayList<>(); // Stores all the meetings

    public Workspace(String emailId) {
        super(emailId,Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        this.calendar.add(meeting);
    }

    public int findMaxMeetings(){

        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList();

        for(int i = 0; i < this.calendar.size(); i++) {
            endTimes.add(Pair.of(((Meeting)this.calendar.get(i)).getEndTime(), i));
        }
        Collections.sort(endTimes);

        LocalTime time_limit = (LocalTime)((Pair)endTimes.get(0)).getLeft();
        int count = 0;
        if (!endTimes.isEmpty()) {
            count++;
        }

        for(int i = 1; i < endTimes.size(); ++i) {
            if (((Meeting)this.calendar.get((Integer)((Pair)endTimes.get(i)).getRight())).getStartTime().compareTo(time_limit) > 0) {
                count++;
                time_limit = (LocalTime)((Pair)endTimes.get(i)).getLeft();
            }
        }

        return count;
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

    }
}