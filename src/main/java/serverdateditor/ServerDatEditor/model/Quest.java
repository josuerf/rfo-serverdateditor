package serverdateditor.ServerDatEditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import serverdateditor.ServerDatEditor.ScriptData;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quest extends ScriptData {
    private long idx;
    private byte[] code = new byte[64];
    private int m_nLimLv;
    private int m_nQuestType;
    private int m_bQuestRepeat;
    private long m_dRepeatTime;
    private int m_nDifficultyLevel;
    private int m_n2;
    private int m_bSelectQuestMenual;
    private int m_bCompQuestType;
    private ActionNode[] m_ActionNode = new ActionNode[3];
    private int m_nMaxLevel;
    private long m_dConsExp;
    private int m_nConsContribution;
    private int m_nConsDalant;
    private int m_nConspvppoint;
    private int m_nConsGold;
    private int m_bSelectConsITMenual;
    private QuestRewardItem[] m_RewardItem = new QuestRewardItem[6];
    private QuestRewardMastery[] m_RewardMastery = new QuestRewardMastery[2];
    private byte[] m_strConsSkillCode = new byte[64];
    private int m_nConsSkillCnt;
    private byte[] m_strConsForceCode = new byte[64];
    private int m_nConsForceCnt;
    private byte[][] m_strLinkQuest = new byte[5][64];
    private int m_nLinkQuestGroupID;
    private int m_bFailCheck;
    private QuestFailCondition[] m_QuestFailCond = new QuestFailCondition[3];
    private byte[] m_strFailBriefCode = new byte[64];
    private int m_nLinkDummyCond;
    private byte[] m_strLinkDummyCode = new byte[64];
    private byte[] m_strFailLinkQuest = new byte[64];
    private int m_nViewportType;
    private byte[] m_strViewportCode = new byte[64];
    private int m_nStore_trade;
    private byte[] m_txtQTExp = new byte[64];

}
