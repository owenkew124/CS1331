import java.util.ArrayList;
public class MonsterMash {
    public static void main(String[] args) {
        Monster Danceula = new Monster("Danceula", 40);
        DancingMonster Dracula = new DancingMonster("Dracula", 50, "dance");
        Monster Werewolf = new Monster("Werewolf", 25);
        DancingMonster VVerevvolf = new DancingMonster("VVerevvolf", 50, "d");
        DancingMonster m1 = new DancingMonster("Casper", 50, "d");
        ArrayList<Monster> guestlist = new ArrayList(5);

        guestlist.add(Danceula);
        guestlist.add(Werewolf);
        GuestList Mash = new GuestList(guestlist);
        Mash.addGuest(m1);
        System.out.println((Mash.getBestDancer()));
        Mash.addGuest(VVerevvolf);
        System.out.println((Mash.getBestDancer()));
        Mash.addGuest(Dracula);
        System.out.println((Mash.getBestDancer()));
        System.out.println(Mash.toString());
        System.out.println((Mash.getBestDancer()));
        System.out.println(Mash.toString());
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
        Mash.danceBattle(Dracula, m1);
    }
}
