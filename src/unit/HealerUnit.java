package unit;

import base.BaseUnit;
import base.Buffable;
import constant.UnitConstant;

public class HealerUnit extends BaseUnit implements Buffable {
    private int healingPoint;

    public HealerUnit() {
        super(UnitConstant.HEALER_UNIT_NAME, UnitConstant.HEALER_UNIT_MAX_HP, 0, UnitConstant.HEALER_UNIT_IMAGE_URL);
        this.healingPoint = UnitConstant.HEALER_UNIT_HEALING_POINT;
    }

    @Override
    public void buffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub

    }
}
