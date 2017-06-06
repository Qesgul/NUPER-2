package www.nupter.org.nupter.model;

/**
 * Created by fangzhenyi on 16/2/15.
 */
public class ClassBean {

    /**
     * status : success
     * info : {"info":{"sun":{"first":"","second":"","third":"","fourth":"","fifth":""},"mon":{"first":"计算机网络<br>周一第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"网络安全技术<br>周一第3,4节{第1-18周}<br>张载龙/朱海婷<br>教4－414","third":"通信原理B<br>周一第6,7节{第1-18周}<br>陈雪红<br>教4－415","fourth":"","fifth":"地理信息系统软件应用<br>周一第10,11节{第1-17周|单周}<br>姜杰<br>教4－414<br><br>信号与系统B<br>周一第10,11,12节{第5-18周}<br>余雪勇<br>教4－302"},"tues":{"first":"软件工程(双语)<br>周二第1,2节{第1-17周|单周}<br>王亚石<br>教4－302<br><br>传感器技术<br>周二第1,2节{第2-18周|双周}<br>陈盛<br>教4－302","second":"职业发展与就业创业指导<br>周二第3,4节{第13-18周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font><br><br>职业发展与就业创业指导<br>周二第3,4节{第9-11周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font>","third":"Linux编程<br>周二第6,7节{第1-18周}<br>李养群<br>教4－302","fourth":"","fifth":""},"wed":{"first":"Windows编程<br>周三第1,2节{第1-18周}<br>郭林<br>教4－302","second":"","third":"网络安全技术<br>周三第6,7节{第1-18周}<br>张载龙/朱海婷<br>教4－414","fourth":"","fifth":""},"thur":{"first":"计算机网络<br>周四第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"软件工程(双语)<br>周四第3,4节{第1-18周}<br>王亚石<br>教4－302","third":"传感器技术<br>周四第6,7节{第1-18周}<br>陈盛<br>教4－302","fourth":"","fifth":""},"fri":{"first":"通信原理B<br>周五第1,2节{第1-18周}<br>陈雪红<br>教4－414","second":"","third":"","fourth":"地理信息系统软件应用<br>周五第8,9节{第1-18周}<br>姜杰<br>教4－414","fifth":""},"sat":{"first":"","second":"","third":"","fourth":"","fifth":""}}}
     */

    private String status;
    /**
     * info : {"sun":{"first":"","second":"","third":"","fourth":"","fifth":""},"mon":{"first":"计算机网络<br>周一第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"网络安全技术<br>周一第3,4节{第1-18周}<br>张载龙/朱海婷<br>教4－414","third":"通信原理B<br>周一第6,7节{第1-18周}<br>陈雪红<br>教4－415","fourth":"","fifth":"地理信息系统软件应用<br>周一第10,11节{第1-17周|单周}<br>姜杰<br>教4－414<br><br>信号与系统B<br>周一第10,11,12节{第5-18周}<br>余雪勇<br>教4－302"},"tues":{"first":"软件工程(双语)<br>周二第1,2节{第1-17周|单周}<br>王亚石<br>教4－302<br><br>传感器技术<br>周二第1,2节{第2-18周|双周}<br>陈盛<br>教4－302","second":"职业发展与就业创业指导<br>周二第3,4节{第13-18周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font><br><br>职业发展与就业创业指导<br>周二第3,4节{第9-11周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font>","third":"Linux编程<br>周二第6,7节{第1-18周}<br>李养群<br>教4－302","fourth":"","fifth":""},"wed":{"first":"Windows编程<br>周三第1,2节{第1-18周}<br>郭林<br>教4－302","second":"","third":"网络安全技术<br>周三第6,7节{第1-18周}<br>张载龙/朱海婷<br>教4－414","fourth":"","fifth":""},"thur":{"first":"计算机网络<br>周四第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"软件工程(双语)<br>周四第3,4节{第1-18周}<br>王亚石<br>教4－302","third":"传感器技术<br>周四第6,7节{第1-18周}<br>陈盛<br>教4－302","fourth":"","fifth":""},"fri":{"first":"通信原理B<br>周五第1,2节{第1-18周}<br>陈雪红<br>教4－414","second":"","third":"","fourth":"地理信息系统软件应用<br>周五第8,9节{第1-18周}<br>姜杰<br>教4－414","fifth":""},"sat":{"first":"","second":"","third":"","fourth":"","fifth":""}}
     */

    private InfoEntity info;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        /**
         * sun : {"first":"","second":"","third":"","fourth":"","fifth":""}
         * mon : {"first":"计算机网络<br>周一第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"网络安全技术<br>周一第3,4节{第1-18周}<br>张载龙/朱海婷<br>教4－414","third":"通信原理B<br>周一第6,7节{第1-18周}<br>陈雪红<br>教4－415","fourth":"","fifth":"地理信息系统软件应用<br>周一第10,11节{第1-17周|单周}<br>姜杰<br>教4－414<br><br>信号与系统B<br>周一第10,11,12节{第5-18周}<br>余雪勇<br>教4－302"}
         * tues : {"first":"软件工程(双语)<br>周二第1,2节{第1-17周|单周}<br>王亚石<br>教4－302<br><br>传感器技术<br>周二第1,2节{第2-18周|双周}<br>陈盛<br>教4－302","second":"职业发展与就业创业指导<br>周二第3,4节{第13-18周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font><br><br>职业发展与就业创业指导<br>周二第3,4节{第9-11周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)<\/font>","third":"Linux编程<br>周二第6,7节{第1-18周}<br>李养群<br>教4－302","fourth":"","fifth":""}
         * wed : {"first":"Windows编程<br>周三第1,2节{第1-18周}<br>郭林<br>教4－302","second":"","third":"网络安全技术<br>周三第6,7节{第1-18周}<br>张载龙/朱海婷<br>教4－414","fourth":"","fifth":""}
         * thur : {"first":"计算机网络<br>周四第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302","second":"软件工程(双语)<br>周四第3,4节{第1-18周}<br>王亚石<br>教4－302","third":"传感器技术<br>周四第6,7节{第1-18周}<br>陈盛<br>教4－302","fourth":"","fifth":""}
         * fri : {"first":"通信原理B<br>周五第1,2节{第1-18周}<br>陈雪红<br>教4－414","second":"","third":"","fourth":"地理信息系统软件应用<br>周五第8,9节{第1-18周}<br>姜杰<br>教4－414","fifth":""}
         * sat : {"first":"","second":"","third":"","fourth":"","fifth":""}
         */

        private InfoEntityl info;

        public void setInfo(InfoEntityl info) {
            this.info = info;
        }

        public InfoEntityl getInfo() {
            return info;
        }

        public static class InfoEntityl {
            /**
             * first :
             * second :
             * third :
             * fourth :
             * fifth :
             */

            private SunEntity sun;
            /**
             * first : 计算机网络<br>周一第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302
             * second : 网络安全技术<br>周一第3,4节{第1-18周}<br>张载龙/朱海婷<br>教4－414
             * third : 通信原理B<br>周一第6,7节{第1-18周}<br>陈雪红<br>教4－415
             * fourth :
             * fifth : 地理信息系统软件应用<br>周一第10,11节{第1-17周|单周}<br>姜杰<br>教4－414<br><br>信号与系统B<br>周一第10,11,12节{第5-18周}<br>余雪勇<br>教4－302
             */

            private MonEntity mon;
            /**
             * first : 软件工程(双语)<br>周二第1,2节{第1-17周|单周}<br>王亚石<br>教4－302<br><br>传感器技术<br>周二第1,2节{第2-18周|双周}<br>陈盛<br>教4－302
             * second : 职业发展与就业创业指导<br>周二第3,4节{第13-18周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)</font><br><br>职业发展与就业创业指导<br>周二第3,4节{第9-11周}<br>张力<br>教4－302<br><br><font color='red'>(停0175)</font>
             * third : Linux编程<br>周二第6,7节{第1-18周}<br>李养群<br>教4－302
             * fourth :
             * fifth :
             */

            private TuesEntity tues;
            /**
             * first : Windows编程<br>周三第1,2节{第1-18周}<br>郭林<br>教4－302
             * second :
             * third : 网络安全技术<br>周三第6,7节{第1-18周}<br>张载龙/朱海婷<br>教4－414
             * fourth :
             * fifth :
             */

            private WedEntity wed;
            /**
             * first : 计算机网络<br>周四第1,2节{第1-18周}<br>厉东明/赵学健<br>教4－302
             * second : 软件工程(双语)<br>周四第3,4节{第1-18周}<br>王亚石<br>教4－302
             * third : 传感器技术<br>周四第6,7节{第1-18周}<br>陈盛<br>教4－302
             * fourth :
             * fifth :
             */

            private ThurEntity thur;
            /**
             * first : 通信原理B<br>周五第1,2节{第1-18周}<br>陈雪红<br>教4－414
             * second :
             * third :
             * fourth : 地理信息系统软件应用<br>周五第8,9节{第1-18周}<br>姜杰<br>教4－414
             * fifth :
             */

            private FriEntity fri;
            /**
             * first :
             * second :
             * third :
             * fourth :
             * fifth :
             */

            private SatEntity sat;

            public void setSun(SunEntity sun) {
                this.sun = sun;
            }

            public void setMon(MonEntity mon) {
                this.mon = mon;
            }

            public void setTues(TuesEntity tues) {
                this.tues = tues;
            }

            public void setWed(WedEntity wed) {
                this.wed = wed;
            }

            public void setThur(ThurEntity thur) {
                this.thur = thur;
            }

            public void setFri(FriEntity fri) {
                this.fri = fri;
            }

            public void setSat(SatEntity sat) {
                this.sat = sat;
            }

            public SunEntity getSun() {
                return sun;
            }

            public MonEntity getMon() {
                return mon;
            }

            public TuesEntity getTues() {
                return tues;
            }

            public WedEntity getWed() {
                return wed;
            }

            public ThurEntity getThur() {
                return thur;
            }

            public FriEntity getFri() {
                return fri;
            }

            public SatEntity getSat() {
                return sat;
            }

            public static class SunEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class MonEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class TuesEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class WedEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class ThurEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class FriEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }

            public static class SatEntity {
                private String first;
                private String second;
                private String third;
                private String fourth;
                private String fifth;

                public void setFirst(String first) {
                    this.first = first;
                }

                public void setSecond(String second) {
                    this.second = second;
                }

                public void setThird(String third) {
                    this.third = third;
                }

                public void setFourth(String fourth) {
                    this.fourth = fourth;
                }

                public void setFifth(String fifth) {
                    this.fifth = fifth;
                }

                public String getFirst() {
                    return first;
                }

                public String getSecond() {
                    return second;
                }

                public String getThird() {
                    return third;
                }

                public String getFourth() {
                    return fourth;
                }

                public String getFifth() {
                    return fifth;
                }
            }
        }
    }
}
