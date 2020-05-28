package serverdateditor.ServerDatEditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestRewardItem extends AuxClass {
    private byte[] strConsITCode = new byte[64];
    private int nConsITCnt;
    private int nLinkQuestIdx;
}
