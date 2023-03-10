package unit;

import base.Attackable;
import base.BaseUnit;
import constant.PotionConstant;
import constant.UnitConstant;
import logic.SquareOwnerState;
import util.AudioUtil;

public class ShooterUnit extends BaseUnit implements Attackable {
    public ShooterUnit(SquareOwnerState state) {
        super(UnitConstant.SHOOTER_UNIT_NAME, UnitConstant.SHOOTER_UNIT_MAX_HP, UnitConstant.SHOOTER_UNIT_POWER,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.SHOOTER_UNIT_IMAGE_1
                        : UnitConstant.SHOOTER_UNIT_IMAGE_2,
                UnitConstant.SHOOTER_UNIT_IMAGE_MOVE, UnitConstant.SHOOTER_UNIT_IMAGE_ATTACK);
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
