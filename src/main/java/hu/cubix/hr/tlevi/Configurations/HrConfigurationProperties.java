package hu.cubix.hr.tlevi.Configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigurationProperties {

    private IncreaseSalary increaseSalary;

    public IncreaseSalary getIncreaseSalary() {
        return increaseSalary;
    }

    public void setIncreaseSalary(IncreaseSalary increaseSalary) {
        this.increaseSalary = increaseSalary;
    }

    public static class IncreaseSalary {
        private Smart smart;
        private Defaultpercent defaultpercent;

        public Smart getSmart() {
            return smart;

        }

        public void setSmart(Smart smart) {
            this.smart = smart;
        }

        public Defaultpercent getDefaultpercent() {
            return defaultpercent;
        }

        public void setDefaultpercent(Defaultpercent defaultpercent) {
            this.defaultpercent = defaultpercent;
        }

        public static class Smart {
            private double limitHighest;
            private double limitMiddle;
            private double limitSmallest;
            private int percentHighest;
            private int percentMiddle;
            private int percentSmallest;

            public double getLimitHighest() {
                return limitHighest;
            }

            public void setLimitHighest(double limitHighest) {
                this.limitHighest = limitHighest;
            }

            public double getLimitMiddle() {
                return limitMiddle;
            }

            public void setLimitMiddle(double limitMiddle) {
                this.limitMiddle = limitMiddle;
            }

            public double getLimitSmallest() {
                return limitSmallest;
            }

            public void setLimitSmallest(double limitSmallest) {
                this.limitSmallest = limitSmallest;
            }

            public int getPercentHighest() {
                return percentHighest;
            }

            public void setPercentHighest(int percentHighest) {
                this.percentHighest = percentHighest;
            }

            public int getPercentMiddle() {
                return percentMiddle;
            }

            public void setPercentMiddle(int percentMiddle) {
                this.percentMiddle = percentMiddle;
            }

            public int getPercentSmallest() {
                return percentSmallest;
            }

            public void setPercentSmallest(int percentSmallest) {
                this.percentSmallest = percentSmallest;
            }
        }

        public static class Defaultpercent {
            private int percent;

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }
        }
    }

}
