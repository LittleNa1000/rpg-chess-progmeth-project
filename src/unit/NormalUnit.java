package unit;

import base.Attackable;
import base.BaseUnit;
import constant.PotionConstant;
import constant.UnitConstant;
import logic.SquareOwnerState;
import util.AudioUtil;

public class NormalUnit extends BaseUnit implements Attackable {

    public NormalUnit(SquareOwnerState state) {
        super(UnitConstant.NORMAL_UNIT_NAME, UnitConstant.NORMAL_UNIT_MAX_HP, UnitConstant.NORMAL_UNIT_POWER,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.NORMAL_UNIT_IMAGE_1
                        : UnitConstant.NORMAL_UNIT_IMAGE_2,
                UnitConstant.NORMAL_UNIT_IMAGE_MOVE, UnitConstant.NORMAL_UNIT_IMAGE_ATTACK);
    }

    @Override
    public void upgrade() {
        setPower(getPower() + PotionConstant.BUFF_POTION_POWER);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        if (!(unit instanceof FlyingUnit)) {
            unit.reduceHealth(this.getPower());
            AudioUtil.playSound("attack-melee.wav");
        }
    }
}
