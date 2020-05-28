package serverdateditor.ServerDatEditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestRewardMastery extends AuxClass {
    private int nConsMasteryID;
    private int nConsMasterySubID;
    private int nConsMasteryCnt;
}
