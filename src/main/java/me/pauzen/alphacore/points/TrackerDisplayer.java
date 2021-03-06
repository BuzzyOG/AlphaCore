/*
 *  Created by Filip P. on 2/2/15 11:16 PM.
 */

package me.pauzen.alphacore.points;

import me.pauzen.alphacore.listeners.ListenerImplementation;
import me.pauzen.alphacore.places.Place;
import me.pauzen.alphacore.players.CorePlayer;
import me.pauzen.alphacore.players.data.Tracker;

public class TrackerDisplayer extends ListenerImplementation {

    private CorePlayer corePlayer;
    private Tracker    tracker;
    private Place      place;
    private int        oldLevel;

    public TrackerDisplayer(Place place, CorePlayer corePlayer, Tracker tracker) {
        super();
        this.corePlayer = corePlayer;
        this.tracker = tracker;
        this.place = place;
        this.oldLevel = corePlayer.getPlayer().getLevel();
        corePlayer.getPlayer().setLevel(tracker.getValue());
    }

    public void update() {
        if (place != corePlayer.getCurrentPlace()) {
            revert();
            return;
        }
        corePlayer.getPlayer().setLevel(tracker.getValue());
    }

    public void revert() {
        corePlayer.getPlayer().setLevel(oldLevel);
    }
}
