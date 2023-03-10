package unit;

import base.Attackable;
import base.BaseUnit;
import constant.PotionConstant;
import constant.UnitConstant;
import logic.SquareOwnerState;
import util.AudioUtil;

public class FlyingUnit extends BaseUnit implements Attackable {
    public FlyingUnit(SquareOwnerState state) {
        super(UnitConstant.FLYING_UNIT_NAME, UnitConstant.FLYING_UNIT_MAX_HP, UnitConstant.FLYING_UNIT_POWER,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.FLYING_UNIT_IMAGE_1
                        : UnitConstant.FLYING_UNIT_IMAGE_2,
                UnitConstant.FLYING_UNIT_IMAGE_MOVE, UnitConstant.FLYING_UNIT_IMAGE_ATTACK);
    }

    @Override
    public void upgrade() {
        setPower(getPower() + PotionConstant.BUFF_POTION_POWER);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.reduceHealth(this.getPower());
        AudioUtil.playSound("attack-ranged.wav", 0.3);
    }
}
