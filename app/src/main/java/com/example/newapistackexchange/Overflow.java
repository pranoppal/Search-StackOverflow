package com.example.newapistackexchange;

public class Overflow {

        private int mans_count;
        private int mscore;
        private String mdisplay_name;
        private String mques_link;
        private int mview_count;
        private String mimage_url;
        private long mc_date;
        private long ml_a_date;

        public Overflow(int ans_count, int score, String display_name, String ques_link, int view_count,String image_url,long c_date,long l_a_date) {
            this.mans_count = ans_count;
            this.mscore = score;
            this.mdisplay_name = display_name;
            this.mques_link = ques_link;
            this.mview_count= view_count;
            this.mimage_url=image_url;
            this.mc_date=c_date;
            this.ml_a_date=l_a_date;
        }

        public int getAns_count() {
            return mans_count;
        }

        public int getScore() {
            return mscore;
        }

        public String getDisplay_name() {
            return mdisplay_name;
        }

        public String getQues_link() {
            return mques_link;
        }

        public int getView_count(){return  mview_count;}

        public String getImage_link() {
        return mimage_url;
    }

        public long getC_date(){return  mc_date;}

        public long getL_A_date(){return  ml_a_date;}

    }

