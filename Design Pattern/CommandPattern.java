interface Command{
    void execute();
    void undo();
}

// Receivers
class Light {
    public void on(){
        System.out.println("Light is On");
    }
    public void off(){
        System.out.println("Light is Off");
    }
}

class Fan {
    public void on(){
        System.out.println("Fan is On");
    }
    public void off(){
        System.out.println("Fan is Off");
    }
}

// Concrete Command of Light
class LightCommand extends Command {
    private Light light;

    public LightCommand(Light l){
        this.light = l;
    }
    public void execute(){
        light.on();
    }
    public void undo(){
        light.off();
    }
}

class FanCommand extends Command {
    private Fan fan;

    public FanCommand(Fan f){
        this.fan = f;
    }
    public void execute(){
        fan.on();
    }
    public void undo(){
        fan.off();    
    }
}

// Remote Controller
class RemoteController{
    private static final int numButton = 4;
    private Command[] buttons;
    private boolean[] buttonPressed;
    
    public RemoteController(){
        button = new Command[numButton];
        buttonPressed = new boolean[numButtons];
        for(int i = 0; i< numButton; i++){
            button[i] = null;
            buttonPressed[i] = false;
        }
    }
    public void setCommand(int idx, Command cmd){
        if(idx >= 0 && idx < numButton){
            button[idx] = cmd;
            buttonPressed[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < numButtons && buttons[idx] != null) {
            if (!buttonPressed[idx]) {
                buttons[idx].execute(); // when buttone is pressed !
            } else {
                buttons[idx].undo(); // if you call twice then if earlier if off then ON-> OFF and if on then OFF->ON
            }
            buttonPressed[idx] = !buttonPressed[idx]; // set to true
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    } 

    public class CommandPattern{
        public static void main(String[] args) {
            Light livingRoomLight = new Light();
            Fan ceilingFan = new Fan();

            RemoteController remote = new RemoteController();

            remote.setCommand(0, new LightCommand(livingRoomLight));
            remote.setCommand(1, new FanCommand(ceilingFan));

            // Button Pressed
            System.out.println("--- Toggling Light Button 0 ---");
            remote.pressButton(0); // ON
            remote.pressButton(0); // OFF

            System.out.println("--- Toggling Fan Button 1 ---");
            remote.pressButton(1);
            remote.pressButton(1);

            System.out.println("Gives you the Default Message given in the pressButton!");
            remote.pressButton(2);
        }
    }
}
