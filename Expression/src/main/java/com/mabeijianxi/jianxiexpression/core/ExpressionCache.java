package com.mabeijianxi.jianxiexpression.core;

import com.mabeijianxi.jianxiexpression.R;

import java.util.HashMap;

/**
 * Created by jian on 2016/6/23.
 * mabeijianxi@gmail.com
 */
public class ExpressionCache {
    //    TODO 每页数据资源，可自己定义，自己更改
    public static final String[] page_1 = new String[]{
            "[e]可爱[/e]", "[e]哈哈[/e]", "[e]嘻嘻[/e]",
            "[e]笑哭[/e]", "[e]思考[/e]", "[e]怒骂[/e]",
            "[e]嘘[/e]", "[e]鼓掌[/e]", "[e]左哼哼[/e]",
            "[e]右哼哼[/e]", "[e]亲亲[/e]", "[e]疑问[/e]",
            "[e]拜拜[/e]", "[e]呵呵[/e]", "[e]阴险[/e]",
            "[e]害羞[/e]", "[e]挤眼[/e]", "[e]打脸[/e]",
            "[e]怒[/e]", "[e]累[/e]", ""
    };

    public static final String[] page_2 = new String[]{
            "[e]鄙视[/e]", "[e]馋嘴[/e]", "[e]吃惊[/e]",
            "[e]打哈气[/e]", "[e]悲伤[/e]", "[e]闭嘴[/e]",
            "[e]顶[/e]", "[e]感冒[/e]", "[e]汗[/e]",
            "[e]爱你[/e]", "[e]黑线[/e]", "[e]哼[/e]",
            "[e]花心[/e]", "[e]可怜[/e]", "[e]酷[/e]",
            "[e]困[/e]", "[e]懒得理你[/e]", "[e]钱[/e]",
            "[e]傻眼[/e]", "[e]生病[/e]", ""
    };
    public static final String[] page_3 = new String[]{
            "[e]失望[/e]", "[e]睡觉[/e]", "[e]抓狂[/e]",
            "[e]太开心[/e]", "[e]偷笑[/e]", "[e]吐[/e]",
            "[e]挖鼻屎[/e]", "[e]委屈[/e]", "[e]晕[/e]",
            "[e]帅[/e]", "[e]狗[/e]", "[e]猫[/e]",
            "[e]熊猫[/e]", "[e]兔子[/e]", "[e]猪头[/e]",
            "[e]神兽[/e]", "[e]男孩儿[/e]", "[e]女孩儿[/e]",
            "[e]肥皂[/e]", "[e]奥特曼[/e]", ""
    };
    public static final String[] page_4 = new String[]{
            "[e]给力[/e]", "[e]囧[/e]", "[e]萌[/e]",
            "[e]神马[/e]", "[e]v5[/e]", "[e]双喜[/e]",
            "[e]织[/e]", "[e]不要[/e]", "[e]棒[/e]",
            "[e]六六[/e]", "[e]来[/e]", "[e]ok[/e]",
            "[e]弱[/e]", "[e]握手[/e]", "[e]耶[/e]",
            "[e]赞[/e]", "[e]作揖[/e]", "[e]伤心[/e]",
            "[e]心[/e]", "[e]蛋糕[/e]", ""
    };
    public static final String[] page_5 = new String[]{
            "[e]飞机[/e]", "[e]干杯[/e]", "[e]话筒[/e]",
            "[e]蜡烛[/e]", "[e]礼物[/e]", "[e]绿丝带[/e]",
            "[e]围脖[/e]", "[e]围观[/e]", "[e]音乐[/e]",
            "[e]照相机[/e]", "[e]钟[/e]", "[e]微风[/e]",
            "[e]鲜花[/e]", "[e]太阳[/e]", "[e]月亮[/e]",
            "[e]浮云[/e]", "[e]下雨[/e]", "[e]沙尘暴[/e]",
            "", "", ""
    };

    /**
     * 所有的表情资源id缓存，增加效率
     */
    private static HashMap<String, Integer> allExpressionTable = new HashMap<String, Integer>();

    static {

        allExpressionTable.put(page_1[0], R.drawable.aa_keai
        );
        allExpressionTable.put(page_1[1], R.drawable.ab_haha
        );
        allExpressionTable.put(page_1[2], R.drawable.ac_xixi
        );
        allExpressionTable.put(page_1[3], R.drawable.ad_xiaoku
        );
        allExpressionTable.put(page_1[4], R.drawable.ae_sikao
        );
        allExpressionTable.put(page_1[5], R.drawable.af_numa
        );
        allExpressionTable.put(page_1[6], R.drawable.ag_xu
        );
        allExpressionTable.put(page_1[7], R.drawable.ah_guzhang
        );
        allExpressionTable.put(page_1[8], R.drawable.ai_zuohengheng
        );
        allExpressionTable.put(page_1[9], R.drawable.aj_youhengheng
        );
        allExpressionTable.put(page_1[10], R.drawable.ak_qinqin
        );
        allExpressionTable.put(page_1[11], R.drawable.al_yiwen
        );
        allExpressionTable.put(page_1[12], R.drawable.am_baibai
        );
        allExpressionTable.put(page_1[13], R.drawable.an_hehe
        );
        allExpressionTable.put(page_1[14], R.drawable.ao_yinxian
        );
        allExpressionTable.put(page_1[15], R.drawable.ap_haixiu
        );
        allExpressionTable.put(page_1[16], R.drawable.aq_jiyan
        );
        allExpressionTable.put(page_1[17], R.drawable.ar_dalian
        );
        allExpressionTable.put(page_1[18], R.drawable.as_nu
        );
        allExpressionTable.put(page_1[19], R.drawable.at_lei
        );


        allExpressionTable.put(page_2[0], R.drawable.ba_bishi
        );
        allExpressionTable.put(page_2[1], R.drawable.bb_chanzui
        );
        allExpressionTable.put(page_2[2], R.drawable.bc_chijing
        );
        allExpressionTable.put(page_2[3], R.drawable.bd_dahaqi
        );
        allExpressionTable.put(page_2[4], R.drawable.be_beishang
        );
        allExpressionTable.put(page_2[5], R.drawable.bf_bizui
        );
        allExpressionTable.put(page_2[6], R.drawable.bg_ding
        );
        allExpressionTable.put(page_2[7], R.drawable.bh_ganmao
        );
        allExpressionTable.put(page_2[8], R.drawable.bi_han
        );
        allExpressionTable.put(page_2[9], R.drawable.bj_aini
        );
        allExpressionTable.put(page_2[10], R.drawable.bk_heixian
        );
        allExpressionTable.put(page_2[11], R.drawable.bl_heng
        );
        allExpressionTable.put(page_2[12], R.drawable.bm_huaxin
        );
        allExpressionTable.put(page_2[13], R.drawable.bn_kelian
        );
        allExpressionTable.put(page_2[14], R.drawable.bo_ku
        );
        allExpressionTable.put(page_2[15], R.drawable.bp_kun
        );
        allExpressionTable.put(page_2[16], R.drawable.bq_landelini
        );
        allExpressionTable.put(page_2[17], R.drawable.br_qian
        );
        allExpressionTable.put(page_2[18], R.drawable.bs_shayan
        );
        allExpressionTable.put(page_2[19], R.drawable.bt_shengbing
        );


        allExpressionTable.put(page_3[0], R.drawable.ca_shiwang
        );
        allExpressionTable.put(page_3[1], R.drawable.cb_shuijiao
        );
        allExpressionTable.put(page_3[2], R.drawable.cc_zhuakuang
        );
        allExpressionTable.put(page_3[3], R.drawable.cd_taikaixin
        );
        allExpressionTable.put(page_3[4], R.drawable.ce_touxiao
        );
        allExpressionTable.put(page_3[5], R.drawable.cf_tu
        );
        allExpressionTable.put(page_3[6], R.drawable.cg_wabishi
        );
        allExpressionTable.put(page_3[7], R.drawable.ch_weiqu
        );
        allExpressionTable.put(page_3[8], R.drawable.ci_yun
        );
        allExpressionTable.put(page_3[9], R.drawable.cj_shuai
        );
        allExpressionTable.put(page_3[10], R.drawable.ck_doge
        );
        allExpressionTable.put(page_3[11], R.drawable.cl_miao
        );
        allExpressionTable.put(page_3[12], R.drawable.cm_xiongmao
        );
        allExpressionTable.put(page_3[13], R.drawable.cn_tuzi
        );
        allExpressionTable.put(page_3[14], R.drawable.co_zhutou
        );
        allExpressionTable.put(page_3[15], R.drawable.cp_shenshou
        );
        allExpressionTable.put(page_3[16], R.drawable.cq_nanhaier
        );
        allExpressionTable.put(page_3[17], R.drawable.cr_nvhaier
        );
        allExpressionTable.put(page_3[18], R.drawable.cs_feizao
        );
        allExpressionTable.put(page_3[19], R.drawable.ct_aoteman

        );

        allExpressionTable.put(page_4[0], R.drawable.da_geili
        );
        allExpressionTable.put(page_4[1], R.drawable.db_jiong
        );
        allExpressionTable.put(page_4[2], R.drawable.dc_meng
        );
        allExpressionTable.put(page_4[3], R.drawable.dd_shenma
        );
        allExpressionTable.put(page_4[4], R.drawable.de_v5
        );
        allExpressionTable.put(page_4[5], R.drawable.df_xi
        );
        allExpressionTable.put(page_4[6], R.drawable.dg_zhi
        );
        allExpressionTable.put(page_4[7], R.drawable.dh_buyao
        );
        allExpressionTable.put(page_4[8], R.drawable.di_good
        );
        allExpressionTable.put(page_4[9], R.drawable.dj_haha
        );
        allExpressionTable.put(page_4[10], R.drawable.dk_lai
        );
        allExpressionTable.put(page_4[11], R.drawable.dl_ok
        );
        allExpressionTable.put(page_4[12], R.drawable.dm_ruo
        );
        allExpressionTable.put(page_4[13], R.drawable.dn_woshou
        );
        allExpressionTable.put(page_4[14], R.drawable.do_ye
        );
        allExpressionTable.put(page_4[15], R.drawable.dp_zan
        );
        allExpressionTable.put(page_4[16], R.drawable.dq_zuoyi
        );
        allExpressionTable.put(page_4[17], R.drawable.dr_shangxin
        );
        allExpressionTable.put(page_4[18], R.drawable.ds_xin
        );
        allExpressionTable.put(page_4[19], R.drawable.dt_dangao
        );


        allExpressionTable.put(page_5[0], R.drawable.ea_feiji
        );
        allExpressionTable.put(page_5[1], R.drawable.eb_ganbei
        );
        allExpressionTable.put(page_5[2], R.drawable.ec_huatong
        );
        allExpressionTable.put(page_5[3], R.drawable.ed_lazhu
        );
        allExpressionTable.put(page_5[4], R.drawable.ee_liwu
        );
        allExpressionTable.put(page_5[5], R.drawable.ef_lvsidai
        );
        allExpressionTable.put(page_5[6], R.drawable.eg_weibo
        );
        allExpressionTable.put(page_5[7], R.drawable.eh_weiguan
        );
        allExpressionTable.put(page_5[8], R.drawable.ei_yinyue
        );
        allExpressionTable.put(page_5[9], R.drawable.ej_zhaoxiangji
        );
        allExpressionTable.put(page_5[10], R.drawable.ek_zhong
        );
        allExpressionTable.put(page_5[11], R.drawable.el_weifeng
        );
        allExpressionTable.put(page_5[12], R.drawable.em_xianhua
        );
        allExpressionTable.put(page_5[13], R.drawable.en_taiyang
        );
        allExpressionTable.put(page_5[14], R.drawable.eo_yueliang
        );
        allExpressionTable.put(page_5[15], R.drawable.ep_fuyun
        );
        allExpressionTable.put(page_5[16], R.drawable.eq_xiayu
        );
        allExpressionTable.put(page_5[17], R.drawable.er_shachenbao
        );
    }

    /**
     * 最近使用表情缓存
     */
    private static String[] recentExpression = new String[21];
    /**
     * tab
     */
    private static String[] pageTitle;

    /**
     * 得到每类表情的标签，可拓展
     *
     * @return
     */
    public static String[] getPageTitle() {
//        String category_1 = "最近";
        String category_2 = "表情";
//        String category_3 = "表二";
        if (pageTitle == null) {
            pageTitle = new String[]{category_2};//category_1,
        }
        return pageTitle;
    }

    public static String[] getRecentExpression() {
        return recentExpression;
    }

    public static HashMap<String, Integer> getAllExpressionTable() {
        return allExpressionTable;
    }
}