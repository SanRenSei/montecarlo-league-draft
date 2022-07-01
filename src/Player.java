import static constants.Constants.*;

public enum Player {

    INJUSTICE(GOLD_1, TOP, JNG, 5, 0),
    IWAY(DIAMOND_4, TOP, MID, 7, 1),
    GLOVELY(BRONZE_1, TOP, SUP, 2, 2),
    REDRAIDER(SILVER_3, TOP, MID, 3, 3),
    PPDISCHARGE(GOLD_2, TOP, MID, 4, 4),
    BLUERANGER(BRONZE_1, JNG, MID, 2, 5),
    EMERALDRANGER(GOLD_4, JNG, TOP, 4, 6),
    RANGERR(PLAT_4, JNG, TOP, 5, 7),
    YAMIBOI28(GOLD_3, JNG, SUP, 4, 8),
    MYHOLEISSTICKY(PLAT_3, JNG, TOP, 5, 9),
    XITACHI4HIKAGEX(SILVER_3, JNG, MID, 3, 10),
    BAMSHOT(BRONZE_2, MID, TOP, 2, 11),
    SWARTZRANGER(SILVER_3, MID, JNG, 3, 12),
    MORTIFIEDMORTY(GOLD_3, MID, JNG, 4, 13),
    MARVELOUSRANGER(BRONZE_2, MID, SUP, 2, 14),
    KATINTHTEBOX(GOLD_2, MID, JNG, 4, 15),
    ML7(SILVER_4, MID, TOP, 2, 16),
    HMAC(BRONZE_3, MID, TOP, 2, 17),
    REDRANGER(PLAT_4, BOT, SUP, 5, 18),
    PROMINENCEPLAYER(PLAT_2, BOT, SUP, 6, 19),
    AMETHYSTRANGER(SILVER_3, BOT, SUP, 3, 20),
    THYPIGBENUS(SILVER_3, BOT, JNG, 3, 21),
    THESAWFTCAWK(BRONZE_4, BOT, TOP, 1, 22),
    CONSUMESAMOSA(PLAT_3, SUP, MID, 5, 23),
    JERICHO(GOLD_2, SUP, TOP, 4, 24),
    EVAMARIE(BRONZE_1, SUP, MID, 2, 25),
    GECKO7717(GOLD_3, SUP, TOP, 4, 26),
    PANSY88(SILVER_1, SUP, TOP, 3, 27),
    MAGIKMAN(BRONZE_4, SUP, MID, 1, 28),
    GAWGEE(DIAMOND_4, SUP, MID, 7, 29)
    ;

    int strength;
    int mainRole;
    int secondaryRole;
    int cost;
    int index;

    Player(int strength,int mainRole,int secondaryRole,int cost, int index) {
        this.strength = strength;
        this.mainRole = mainRole;
        this.secondaryRole = secondaryRole;
        this.cost = cost;
        this.index = index;
    }

    int getStrengthInRole(int role) {
        if (this.mainRole==role) {
            return this.strength;
        }
        if (this.secondaryRole==role) {
            return this.strength-1;
        }
        return Math.max(this.strength-4, 0);
    }

}
