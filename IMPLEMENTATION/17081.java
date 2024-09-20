import java.util.*;

public class Main {


    static Scanner sc = new Scanner(System.in);
    static Map<Coordinate, Monster> monsterMap = new HashMap<>();
    static Map<Coordinate, Equipment> equipmentMap = new HashMap<>();

    static int n, m;
    static char[][] graph = new char[102][102];
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int monsterCnt = 0;
    static int treasureBox = 0;

    static void printGraph(Hero hero) {
        if (hero.live) graph[hero.x][hero.y] = '@';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(graph[i][j] + "");
            }
            System.out.println();
        }
    }

    static void setMonster() {
        for (int i = 0; i < monsterCnt; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            String monsterName = sc.next();
            int monsterAtt = sc.nextInt();
            int monsterDef = sc.nextInt();
            int monsterHp = sc.nextInt();
            int monsterExp = sc.nextInt();
            monsterMap.put(new Coordinate(x, y),
                    new Monster(
                            monsterName,
                            new Attack(monsterAtt),
                            new Defensive(monsterDef),
                            new Hp(monsterHp),
                            new Exp(monsterExp)
                    )
            );
        }

    }

    static void setTreasureBox() {
        for (int i = 0; i < treasureBox; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            String equipmentType = sc.next();
            String ability = sc.next();
            equipmentMap.put(new Coordinate(x, y), new Equipment(equipmentType, ability));
        }
    }

    static boolean isCheckTheBoss(Hero hero) {
        return graph[hero.x][hero.y] == 'M';
    }

    static void findSpikeOrTreasureOrMonster(Hero hero) {
        char isChar = graph[hero.x][hero.y];
        if (isChar == '^') runSpikeDamage(hero);
        else if (isChar == 'B') {
            hero.findTreasure(equipmentMap);
            graph[hero.x][hero.y] = '.';
        } else {
            Monster monster = findMonster(hero);
            boolean isMonsterCatch = fightRun(hero, monster);
            if (isMonsterCatch) graph[hero.x][hero.y] = '.';
        }

    }

    static Monster findMonster(Hero hero) {
        return monsterMap.get(new Coordinate(hero.x, hero.y));
    }

    static void move(int nx, int ny, Hero hero) {
        hero.x = nx;
        hero.y = ny;
        if (graph[hero.x][hero.y] != '.') findSpikeOrTreasureOrMonster(hero);
    }

    static void runSpikeDamage(Hero hero) {
        hero.ableDxAbility();
        if (hero.hp.rem <= 0) hero.ableReAbility();

        hero.isCheckRemZero("YOU HAVE BEEN KILLED BY SPIKE TRAP..");

    }

    static void run(Hero hero, String[] passedTurns) {
        setMonster();
        setTreasureBox();

        for (String turn : passedTurns) {
            hero.countTurn();
            int dirNumber = Dir.getDirNumber(turn);
            int nx = hero.x + dx[dirNumber];
            int ny = hero.y + dy[dirNumber];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (graph[hero.x][hero.y] == '^' && graph[nx][ny] == '#') runSpikeDamage(hero);
                else if (graph[nx][ny] == '#') continue;
                else move(nx, ny, hero);
            } else if (graph[hero.x][hero.y] == '^') runSpikeDamage(hero);
            if (!hero.isCheckAlive() || hero.bossKilled) break;
        }
    }

    static void gameStart(Hero hero, Monster monster) {
        boolean isFirstFight = true;
        while (true) {
            boolean isHuAbilityAble = false;
            int monsterDamage = monster.monsterAtt.attack;
            int heroDamage = hero.att.totalDamage;

            if (isFirstFight) {
                heroDamage = hero.ableCoAbility();
                if (isCheckTheBoss(hero)) isHuAbilityAble = hero.ableHuAbility();
                isFirstFight = false;
            }

            monster.monsterHp.rem -= Math.max(1, heroDamage - monster.monsterDef.armor);
            if (monster.monsterHp.rem <= 0) break;
            if (!isHuAbilityAble) hero.hp.rem -= Math.max(1, monsterDamage - hero.def.totalArmor);
            if (hero.hp.rem <= 0) break;
        }
    }


    static boolean catchTheMonsterOrBoss(Hero hero, Monster monster) {
        hero.catchTheMonster(monster);
        if (isCheckTheBoss(hero)) hero.killTheBoss();
        monsterMap.remove(new Coordinate(hero.x, hero.y));
        return true;
    }

    static boolean fightRun(Hero hero, Monster monster) {
        gameStart(hero, monster);
        if (hero.isDie(monster)) return false;
        return catchTheMonsterOrBoss(hero, monster);
    }

    static Hero makeHeroAndGraphInit() {
        int firstX = 0;
        int firstY = 0;
        for (int i = 0; i < n; i++) {
            String inString = sc.next();
            for (int j = 0; j < m; j++) {
                char word = inString.charAt(j);
                if (word == 'B') treasureBox++;
                else if (word == 'M' || word == '&') monsterCnt++;
                else if (word == '@') {
                    firstX = i;
                    firstY = j;
                    graph[i][j] = '.';
                    continue;
                }
                graph[i][j] = word;
            }
        }
        return new Hero(firstX, firstY);
    }

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        Hero hero = makeHeroAndGraphInit();
        String[] passedTurns = sc.next().split("");
        run(hero, passedTurns);
        printResult(hero);
    }

    static void printResult(Hero hero) {
        printGraph(hero);
        System.out.println(hero);
    }
}

class Monster {
    String monsterName;
    Attack monsterAtt;
    Defensive monsterDef;
    Hp monsterHp;
    Exp monsterExp;

    Monster(String monsterName, Attack monsterAtt, Defensive monsterDef, Hp monsterHp, Exp monsterExp) {
        this.monsterName = monsterName;
        this.monsterAtt = monsterAtt;
        this.monsterDef = monsterDef;
        this.monsterHp = monsterHp;
        this.monsterExp = monsterExp;

    }

    public String toString() {
        return String.format("\nName : %s\nAtt : %s\nDef : %s\nHP: %s\nExp : %s\n",
                monsterName, monsterAtt, monsterDef, monsterHp, monsterExp);
    }

}


class Hero {
    int firstX;
    int firstY;
    int x;
    int y;
    boolean live = true;
    boolean bossKilled = false;
    int passedTurns = 0;
    Level level = new Level();
    Hp hp = new Hp();
    Attack att = new Attack();
    Defensive def = new Defensive();
    Exp exp = new Exp();
    Accessories accessories = new Accessories();
    String END_MESSAGE = "Press any key to continue.";


    Hero(int x, int y) {
        this.x = x;
        this.y = y;
        this.firstX = x;
        this.firstY = y;
    }

    void countTurn() {
        passedTurns++;
    }
    void isDie() {
        this.live = false;
    }

    boolean isCheckAlive() {
        return live;

    }


    public void isCheckRemZero(String message) {
        if (hp.rem < 1) {
            hp.rem = 0;
            isDie();
            END_MESSAGE = message;
        }
    }

    public void findTreasure(Map<Coordinate, Equipment> equipmentMap) {
        Equipment equipment = equipmentMap.get(new Coordinate(x, y));

        // 장신구
        if (equipment.isTypeCheck("O")) accessories.addAccessories(equipment);
        else if (equipment.isTypeCheck("W")) att.setWeapon(equipment.ability);
        else def.setArmor(equipment.ability);

    }

    public void isLevelUp(int addExp) {
        exp.expUp(addExp);
        level.levelUp(exp, hp, att, def);
    }


    public boolean isCheckOverMaxHp() {
        return hp.rem > hp.cur;
    }


    public void killTheBoss() {
        END_MESSAGE = "YOU WIN!";
        bossKilled = true;
    }

    public boolean isDie(Monster monster) {
        if (hp.rem <= 0) {
            if (accessories.findAbility("RE")) {
                ableReAbility();
                monster.monsterHp.rem = monster.monsterHp.cur;
            } else isCheckRemZero(String.format("YOU HAVE BEEN KILLED BY %s..", monster.monsterName));
            return true;

        }
        return false;
    }

    public void catchTheMonster(Monster monster) {
        ableHrAbility();
        if (accessories.findAbility("EX")) isLevelUp((int) (monster.monsterExp.exp * 1.2));
        else isLevelUp(monster.monsterExp.exp);
    }

    public void ableHrAbility() {
        if (accessories.findAbility("HR")) {
            hp.rem += 3;
            if (isCheckOverMaxHp()) hp.recovery();
        }
    }

    void ableDxAbility() {
        int spikeDamage = 5;
        if (accessories.findAbility("DX")) {
            spikeDamage = 1;
            hp.rem -= spikeDamage;
        } else hp.rem -= spikeDamage;
    }

    int ableCoAbility() {
        if (accessories.findAbility("CO")) {
            if (accessories.findAbility("DX")) return att.totalDamage * 3;
            return att.totalDamage * 2;
        }
        return att.totalDamage;
    }

    boolean ableHuAbility() {
        if (accessories.findAbility("HU")) {
            hp.recovery();
            return true;
        }
        return false;
    }

    public void ableReAbility() {
        if (accessories.findAbility("RE")) {
            hp.recovery();
            x = firstX;
            y = firstY;
            accessories.removeRe();
        }
    }

    public String toString() {
        return String.format("Passed Turns : %d\nLV : %s \nHP : %s\nATT : %s\nDEF : %s\nEXP : %s%s\n%s ",
                passedTurns, level, hp, att, def, exp, level.level * 5, END_MESSAGE);
    }
}

class Level {
    int level = 1;

    public String toString() {
        return level + "";
    }

    public void levelUp(Exp exp, Hp hp, Attack att, Defensive def) {
        if (exp.isLevelUp(level)) {
            level++;
            exp.init();

            hp.upHp();
            att.upDamage();
            def.upArmor();

            att.setTotalDamage();
            def.setTotalArmor();
        }
    }
}

class Exp {
    int exp = 0;

    Exp() {
    }

    Exp(int exp) {
        this.exp = exp;
    }

    public void expUp(int addExp) {
        exp += addExp;
    }

    public boolean isLevelUp(int level) {
        return exp >= (level * 5);
    }

    void init() {
        exp = 0;
    }

    public String toString() {
        return String.format("%d/", exp);
    }
}

class Hp {
    int rem = 20;
    int cur = 20;

    Hp() {
    }

    Hp(int cur) {
        this.cur = cur;
        this.rem = cur;
    }

    void upHp() {
        cur += 5;
        recovery();
    }

    void recovery() {
        rem = cur;
    }

    public String toString() {
        return String.format("%d/%d", rem, cur);
    }
}

class Attack {
    int attack = 2;
    int weaponDamage;
    int totalDamage = 2;

    Attack(int attack) {
        this.attack = attack;
    }

    Attack() {
    }

    void upDamage() {
        attack += 2;
    }

    void setTotalDamage() {
        totalDamage = attack + weaponDamage;
    }

    void setWeapon(String addWeaponDamageString) {
        weaponDamage = Integer.parseInt(addWeaponDamageString);
        setTotalDamage();
    }

    public String toString() {
        return String.format("%d+%d", attack, weaponDamage);
    }
}

class Defensive {
    int armor = 2;
    int wearArmor;
    int totalArmor = 2;

    Defensive() {
    }

    void upArmor() {
        armor += 2;
    }

    void setTotalArmor() {
        this.totalArmor = armor + wearArmor;
    }

    void setArmor(String addArmorString) {
        this.wearArmor = Integer.parseInt(addArmorString);
        setTotalArmor();

    }

    Defensive(int armor) {
        this.armor = armor;
    }

    public String toString() {
        return String.format("%d+%d", armor, wearArmor);
    }
}

class Equipment {
    String equipmentType;
    String ability;

    Equipment(String equipmentType, String ability) {
        this.equipmentType = equipmentType;
        this.ability = ability;
    }

    boolean isTypeCheck(String otherEquipmentType) {
        return equipmentType.equals(otherEquipmentType);
    }


    public String toString() {
        return String.format("[EquipmentType : %s, Ability : %s]", equipmentType, ability);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Equipment) {
            Equipment equipment = (Equipment) obj;
            return equipmentType.equals(equipment.equipmentType) && ability.equals(equipment.ability);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentType, ability);
    }
}

class Accessories {
    List<Accessor> accessoriesList = new ArrayList<>();

    public void addAccessories(Equipment equipment) {
        if (accessoriesList.size() < 4 && isDuplicate(equipment)) {
            accessoriesList.add(new Accessor(equipment));
        }
    }

    public boolean isDuplicate(Equipment otherEquipment) {
        for (Accessor accessor : accessoriesList) {
            if (accessor.equipment.ability.equals(otherEquipment.ability)) return false;
        }
        return true;
    }

    public boolean findAbility(String findAbilityName) {
        return accessoriesList.stream()
                .filter((accessor) -> accessor.equipment.ability.equals(findAbilityName))
                .findFirst()
                .map((accessor) -> true)
                .orElse(false);
    }


    void removeRe() {
        accessoriesList.remove(new Accessor(new Equipment("O", "RE")));
    }
}


class Accessor {
    Equipment equipment;

    Accessor(Equipment equipment) {
        this.equipment = equipment;
    }

    public String toString() {
        return equipment.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Accessor) {
            Accessor accessor = (Accessor) obj;
            return equipment.equals(accessor.equipment);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipment);
    }

}

enum Dir {
    L("L", 0),
    R("R", 1),
    U("U", 2),
    D("D", 3);
    final String alpha;
    final int number;

    Dir(String alpha, int number) {
        this.alpha = alpha;
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    boolean alphaMatchTurns(String turn) {
        return alpha.equals(turn);
    }

    static int getDirNumber(String turn) {
        return Arrays.stream(Dir.values())
                .filter((dir) -> dir.alphaMatchTurns(turn))
                .mapToInt(Dir::getNumber)
                .findFirst()
                .orElse(D.number);
    }

}

class Coordinate {
    int first;
    int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Coordinate(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return first == coordinate.first && second == coordinate.second;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}