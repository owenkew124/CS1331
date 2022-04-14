import java.util.ArrayList;
/**
 * Represents a list of the Guests that are at a Monster Mash Party
 * with a guest list and a best dancer.
 *
 *@author okew3
 *@version 11.0.9.1
 */
public class GuestList {
    private ArrayList<Monster> guests;
    private DancingMonster bestDancer;
    /**
     * Creates a guest list with a provided guest list and a best dancer
     * calculated by comparing the spookiness of each Dancing Monster in
     * the guest list.
     * @param guests a list of the Monster at the party
     */
    public GuestList(ArrayList<Monster> guests) {
        this.guests = guests;
        if (guests.isEmpty()) {
            this.bestDancer = null;
        } else {
            ArrayList<DancingMonster> dancingGuests = new ArrayList<DancingMonster>(guests.size());
            for (int i = guests.size() - 1; i >= 0; i--) {
                if (guests.get(i) instanceof DancingMonster) {
                    DancingMonster danceMon = (DancingMonster) guests.get(i);
                    dancingGuests.add(danceMon);
                }
            }
            if (dancingGuests.size() == 1) {
                this.bestDancer = dancingGuests.get(0);
            } else if (dancingGuests.isEmpty()) {
                this.bestDancer = null;
            } else {
                int bestIndex = dancingGuests.size() - 1;
                for (int i = dancingGuests.size() - 1; i >= 1; i--) {
                    if (dancingGuests.get(bestIndex).compareTo(dancingGuests.get(i - 1)) == 1) {
                        bestIndex = bestIndex;
                    } else if (dancingGuests.get(bestIndex).compareTo(dancingGuests.get(i - 1)) == -1) {
                        bestIndex = i - 1;
                    } else {
                        bestIndex = bestIndex;
                    }
                }
                this.bestDancer = dancingGuests.get(bestIndex);
            }
        }
    }

    /**
     * Creates a guest list with an empty guest list and a null best dancer.
     */
    public GuestList() {
        ArrayList<Monster> empty = new ArrayList<Monster>(0);
        this.guests = empty;
        this.bestDancer = null;
    }

    /**
     * Adds another guest to the list of guests and, if the added guest is a
     * better dancer than the best dancer, the added guest is set as the bestDancer.
     * @param addMon the Monster to be added to the guest list
     */
    public void addGuest(Monster addMon) {
        this.guests.add(addMon);
        if (addMon instanceof DancingMonster) {
            DancingMonster addMon1 = (DancingMonster) addMon;
            if (!(bestDancer == null)) {
                if (addMon.compareTo(bestDancer) == 1) {
                    this.bestDancer = addMon1;
                }
            } else {
                this.bestDancer = addMon1;
            }
        }
    }

    /**
     * @return A String representation of the Guest list
     */
    public String toString() {
        String names = "";
        int numGuests = guests.size();
        int count = 0;
        if (!(guests.isEmpty())) {
            while (numGuests > 1) {
                Monster guest = (Monster) guests.get(count);
                String guestName = guest.getName();
                names = names + guestName + ", ";
                count += 1;
                numGuests -= 1;
            }
            Monster finalGuest = (Monster) guests.get(guests.size() - 1);
            names = names + finalGuest.getName();
            return names;
        } else {
            names = "";
            return names;
        }
    }

    /**
     * Compares two Monsters and based on their spookiness decides who wins a dance battle between the two.
     *
     * @param firstMon the first Monster who is in the dance battle
     * @param secondMon the second Monster who is in the dance battle
     */
    public void danceBattle(Monster firstMon, Monster secondMon) {
        System.out.println(firstMon.toString());
        System.out.println(secondMon.toString());
        if (firstMon instanceof DancingMonster && !(secondMon instanceof DancingMonster)) {
            DancingMonster firstMon1 = (DancingMonster) firstMon;
            firstMon1.wonDance();
            System.out.println(firstMon.getName() + " won the dance battle!");
        } else if (!(firstMon instanceof DancingMonster) && secondMon instanceof DancingMonster) {
            DancingMonster secondMon1 = (DancingMonster) secondMon;
            secondMon1.wonDance();
            System.out.println(secondMon.getName() + " won the dance battle!");
        } else if (!(firstMon instanceof DancingMonster) && !(secondMon instanceof DancingMonster)) {
            System.out.println("And this dance battle is a tie!");
        } else if (firstMon instanceof DancingMonster && secondMon instanceof DancingMonster) {
            DancingMonster firstMon1 = (DancingMonster) firstMon;
            DancingMonster secondMon1 = (DancingMonster) secondMon;
            int firstScore = firstMon1.dance();
            int secondScore = secondMon1.dance();
            if (firstScore > secondScore) {
                firstMon1.wonDance();
                System.out.println(firstMon.getName() + " won the dance battle!");
            } else if (secondScore > firstScore) {
                secondMon1.wonDance();
                System.out.println(secondMon.getName() + " won the dance battle!");
            } else {
                System.out.println("And this dance battle is a tie!");
            }
        }
    }

    /**
     * Getter for the best dancer.
     * @return the best dancer
     */
    public DancingMonster getBestDancer() {
        return bestDancer;
    }
}