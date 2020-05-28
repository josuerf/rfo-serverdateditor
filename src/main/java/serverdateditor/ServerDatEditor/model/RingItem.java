package serverdateditor.ServerDatEditor.model;

import com.google.common.primitives.UnsignedInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import serverdateditor.ServerDatEditor.ScriptData;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RingItem extends ScriptData {
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
}
