package state;

import java.util.HashMap;

public class stateUtils {

    public static final HashMap<Long,State> stateCache =new HashMap<Long,State>();

    public static State getUserState (Long userId){
        State userState = null;
        if (stateCache.containsKey(userId)){
            userState = (State) stateCache.get(userId);
        } else {
            userState = State.STATE_NOTSTARTED;
        }
        return userState;
    }

    public static State setNextUserState (Long userId){
        State userState = null;
        try{
            if (stateCache.containsKey(userId)){
                userState = (State) stateCache.get(userId);
            } else {
                userState = State.STATE_NOTSTARTED;
            }
            userState = userState.getNextState();
            stateCache.put(userId,userState);
            System.out.println(stateCache);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return userState;
    }


}
