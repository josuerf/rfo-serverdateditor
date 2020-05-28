package serverdateditor.ServerDatEditor.model;

import serverdateditor.ServerDatEditor.ScriptData;
import com.google.common.primitives.UnsignedInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmuletItem extends ScriptData {
    private long idx;
    private byte[] code = new byte[64];
    private int bExist;
    private byte[] strModel = new byte[64];
    private int nIconIDX;
    private byte[] strName = new byte[64];
    private int nKindClt;
    private int nItemGrade;
    private int nFixPart;
    private byte[] strCivil = new byte[64];
    private int nLevelLim;
    private int nUpLevelLim;
    private int nClassGradeLim;
    private int nMoney;
    private int nStdPrice;
    private int nStdPoint;
    private int nGoldPoint;
    private int nKillPoint;
    private int nProcPoint;
    private int nStoragePrice;
    private int bAbr;
    private int nEffState;
    private float fFireTol;
    private float fWaterTol;
    private float fSoilTol;
    private float fWindTol;
    private int nEff1Code;
    private float fEff1Unit;
    private int nEff2Code;
    private float fEff2Unit;
    private int nEff3Code;
    private float fEff3Unit;
    private int nEff4Code;
    private float fEff4Unit;
    private int bSell;
    private int bExchange;
    private int bGround;
    private int bStoragePossible;
    private int bUseableNormalAcc;
    private byte[] strTooltipIndex = new byte[64];
    private int bIsCash;
    private int bIsTime;

    @Override
    public String toString() {
        return "AmuletItem{" +
                "idx=" + idx +
                ", code=" + new String(code) +
                ", bExist=" + bExist +
                ", strModel=" + new String(strModel) +
                ", nIconIDX=" + nIconIDX +
                ", strName=" + new String(strName) +
                ", nKindClt=" + nKindClt +
                ", nItemGrade=" + nItemGrade +
                ", nFixPart=" + nFixPart +
                ", strCivil=" + new String(strCivil) +
                ", nLevelLim=" + nLevelLim +
                ", nUpLevelLim=" + nUpLevelLim +
                ", nClassGradeLim=" + nClassGradeLim +
                ", nMoney=" + nMoney +
                ", nStdPrice=" + nStdPrice +
                ", nStdPoint=" + nStdPoint +
                ", nGoldPoint=" + nGoldPoint +
                ", nKillPoint=" + nKillPoint +
                ", nProcPoint=" + nProcPoint +
                ", nStoragePrice=" + nStoragePrice +
                ", bAbr=" + bAbr +
                ", nEffState=" + nEffState +
                ", fFireTol=" + fFireTol +
                ", fWaterTol=" + fWaterTol +
                ", fSoilTol=" + fSoilTol +
                ", fWindTol=" + fWindTol +
                ", nEff1Code=" + nEff1Code +
                ", fEff1Unit=" + fEff1Unit +
                ", nEff2Code=" + nEff2Code +
                ", fEff2Unit=" + fEff2Unit +
                ", nEff3Code=" + nEff3Code +
                ", fEff3Unit=" + fEff3Unit +
                ", nEff4Code=" + nEff4Code +
                ", fEff4Unit=" + fEff4Unit +
                ", bSell=" + bSell +
                ", bExchange=" + bExchange +
                ", bGround=" + bGround +
                ", bStoragePossible=" + bStoragePossible +
                ", bUseableNormalAcc=" + bUseableNormalAcc +
                ", strTooltipIndex=" + new String(strTooltipIndex) +
                ", bIsCash=" + bIsCash +
                ", bIsTime=" + bIsTime +
                "}";
    }
}
