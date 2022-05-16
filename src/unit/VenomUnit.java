package unit;

import base.Attackable;
import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;

public class VenomUnit extends BaseUnit implements Debuffable {
    private final int poisonPower;
    private int poisonRound;

    public VenomUnit() {
        super(UnitConstant.VENOM_UNIT_NAME, UnitConstant.VENOM_UNIT_MAX_HP, 0, UnitConstant.VENOM_UNIT_IMAGE_URL);
        this.poisonPower = UnitConstant.VENOM_UNIT_POISON_POWER;
        this.poisonRound = UnitConstant.VENOM_UNIT_POISON_ROUND;
    }

    @Override
    public void debuffUnit() {
        // TODO Auto-generated method stub

    }
}
