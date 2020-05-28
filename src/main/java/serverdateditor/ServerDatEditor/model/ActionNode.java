package serverdateditor.ServerDatEditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionNode extends AuxClass {
    private int nActType;
    private byte[] strActSub = new byte[64];
    private byte[] strActSub2 = new byte[64];
    private byte[] strActArea = new byte[64];
    private int nReqAct;
    private int nSetCntPro100;
    private byte[] strLinkQuestItem = new byte[64];
    private int nOrder;
}
