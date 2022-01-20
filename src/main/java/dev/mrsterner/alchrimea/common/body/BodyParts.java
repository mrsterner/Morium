package dev.mrsterner.alchrimea.common.body;


import java.util.Locale;


public enum BodyParts {
    HEAD(),
    TORSO(),
    LEFTARM(),
    RIGHTARM(),
    LEFTLEG(),
    RIGHTLEG(),
    EYES(),
    STOMACH();


    BodyParts(){

    }

    @Override
    public String toString() {
        return "body."+name().toLowerCase(Locale.ROOT);
    }

    public BodyParts fromString(String string){
        return
        string.equals("head") ? HEAD :
        string.equals("torso") ? TORSO :
        string.equals("leftarm") ? LEFTARM :
        string.equals("rightarm") ? RIGHTARM :
        string.equals("leftleg") ? LEFTLEG :
        string.equals("rightleg") ? RIGHTLEG :
        string.equals("stomach") ? STOMACH :
        EYES;
    }

}
