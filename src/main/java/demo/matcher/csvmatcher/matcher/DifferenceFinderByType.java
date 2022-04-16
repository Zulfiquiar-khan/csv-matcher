package demo.matcher.csvmatcher.matcher;

import java.time.LocalDate;
import java.util.function.BiFunction;

import demo.matcher.csvmatcher.model.Transaction;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class DifferenceFinderByType implements DifferenceFinder{
    @NonNull
    private BiFunction<String, String, Float> stringDifferenceCalculator;
    @NonNull
    private BiFunction<Float, Float, Float> numberDifferenceCalculator;
    @NonNull
    private BiFunction<LocalDate, LocalDate, Float> localDateDifferenceCalculator; 

    @Override
    public Float getDifferenceScore(Transaction t1, Transaction t2) {
        float gstinDif = stringDifferenceCalculator.apply(t1.getGstin(), t2.getGstin());
        if(gstinDif<0) return -1f;
        float dateDif = localDateDifferenceCalculator.apply(t1.getDate(), t2.getDate());
        if(dateDif<0) return -1f;
        float billNoDif = stringDifferenceCalculator.apply(t1.getBillNo(), t2.getBillNo());
        if(billNoDif<0) return -1f;
        float gstRateDif = numberDifferenceCalculator.apply(t1.getGstRate(), t2.getGstRate());
        if(gstRateDif<0) return -1f;
        float taxableValueDif = numberDifferenceCalculator.apply(t1.getTaxableValue(), t2.getTaxableValue());
        if(taxableValueDif<0) return -1f;
        float igstDif = numberDifferenceCalculator.apply(t1.getIgst(), t2.getIgst());
        if(igstDif<0) return -1f;
        float cgstDif = numberDifferenceCalculator.apply(t1.getCgst(), t2.getCgst());
        if(cgstDif<0) return -1f;
        float sgstDif = numberDifferenceCalculator.apply(t1.getSgst(), t2.getSgst());
        if(sgstDif<0) return -1f;
        float totalDif = numberDifferenceCalculator.apply(t1.getIgst(), t2.getIgst());
        if(totalDif<0) return -1f;
        return gstinDif + dateDif + billNoDif + gstRateDif + taxableValueDif + igstDif + cgstDif + sgstDif + totalDif;
    }
    
}