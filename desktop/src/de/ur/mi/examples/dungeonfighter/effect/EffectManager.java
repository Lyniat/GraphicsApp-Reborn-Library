package de.ur.mi.examples.dungeonfighter.effect;

import java.util.ArrayList;

public class EffectManager {

    private static ArrayList<Effect> effects = new ArrayList<>();

    private static ArrayList<Effect> toRemoveEffects = new ArrayList<>();

    public void draw(){
        for (Effect effect:effects) {
            effect.draw();
        }

        ArrayList<Effect> newEffects = new ArrayList<>();

        for (Effect effect:effects) {
            if(!toRemoveEffects.contains(effect)){
                newEffects.add(effect);
            }
        }

        effects = newEffects;

        toRemoveEffects.clear();
    }

    public void add(Effect effect){
        effects.add(effect);
    }

    public void remove(Effect effect){
        if(!effects.contains(effect)){
            return;
        }
        toRemoveEffects.add(effect);
    }
}
