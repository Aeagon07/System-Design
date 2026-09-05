// component interface: defines a common interface for Mario and all power-up decorators
interface Character{
    String getAbilities();
}

// Concrete Component: Basic Mario character with no power-ups.
class Mario implements Character{
    public String getAbilities(){
        return "Mario";
    }
}

// implemnets Character shows => 'is-a' releationship 
// defining object character and given to the constructor which take that parameter and assign it...

// Decorator class
abstract class CharacterDecoder implements Character{
    protected Character character;

    public CharacterDecoder(Character c){
        this.character = c;
    }
}

class HeightUp extends CharacterDecoder{
    public HeightUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities() + " with HeightUp";
    }
}

class GunPowderUp extends CharacterDecoder {
    public GunPowderUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities() + " with Gun";
    }
}

class StarPowerUp extends CharacterDecoder {
    public StarPowerUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities() + " with Star Power (Limited Time)"
    }
}

public class DecoratorPattern{
    public static void main(String[] args){

        // Create a basic Mario character.
        Character mario = new Mario();
        System.out.println("Basic Character: " + mario.getAbilities());

        // Decorate Mario with a HeightUp power-up.
        mario = new HeightUp(mario);
        System.out.println("After HeightUp: " + mario.getAbilities());

        // Decorate Mario further with a GunPowerUp.
        mario = new GunPowderUp(mario);
        System.out.println("After GunPowerUp: " + mario.getAbilities());

        // Finally, add a StarPowerUp decoration.
        mario = new StarPowerUp(mario);
        System.out.println("After StarPowerUp: " + mario.getAbilities());
    }
}


