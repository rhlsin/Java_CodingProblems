package fragmadata;

import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class FragmaData {
    // function to get top 4 teams
    static void top(int[] counter, int[] top4, int uniqueTeamLen, List<String> uniqueTeam, String Year) {
        int max = 0, index, i;
        
        int[] temp = new int[uniqueTeamLen];
        
        for(int k = 0;k < uniqueTeamLen; k++){
            temp[k] = counter[k];
        } 
        
        for (int j = 0; j < 4; j++) {
            max = temp[0];
            index = 0;
            for (i = 1; i < temp.length; i++) {
                if (max < temp[i]) {
                    max = temp[i];
                    index = i;
                }
            }
            top4[j] = index;
            temp[index] = Integer.MIN_VALUE;
            
            System.out.println("Year " + Year + " Team "+ uniqueTeam.get(top4[j]) + " Count "+ counter[top4[j]]);            
        }
        System.out.println("");
    }
    
    static void analyzeScore (int[][] deliveriesData,int deliveriesRowCount, int coun, List<String> uniqueTeam, String[][] arr) {
        int i,j,k;
        int fours1;
        int sixes1;
        int fours2;
        int sixes2;
        int totalScore1;
        int totalScore2;
        
        for(i=1; i < coun; i++) {
            fours1 = 0;
            sixes1 = 0;
            fours2 = 0;
            sixes2 = 0;
            totalScore1 = 0;
            totalScore2 = 0;
            for (j=1; j < deliveriesRowCount; j++) {                
                if(deliveriesData[j][0] == i) {                    
                    if(deliveriesData[j][1] == 1) {
                        if(deliveriesData[j][4] == 4) {
                            fours1++;
                        } else if(deliveriesData[j][4] == 6) {
                            sixes1++;
                        }
                        totalScore1 = totalScore1 + deliveriesData[j][5];
                    } else {
                        if(deliveriesData[j][4] == 4) {
                            fours2++;
                        } else if(deliveriesData[j][4] == 6) {
                            sixes2++;
                        }
                        
                        totalScore2 = totalScore2 + deliveriesData[j][5];
                    }
                    deliveriesData[j][11] =  Integer.parseInt(arr[i][0]);
                }                  
            }
            System.out.println("\nYear: " + arr[i][0] + "\nTeamName: " + arr[i][1] + " , Fours:  " + fours1 + " , Sixes: " + sixes1 + " , TotalScore: " + totalScore1);                    
            System.out.println("TeamName: " + arr[i][2] +" , Fours:  " + fours2 + " , Sixes: " + sixes2 + " , TotalScore: " + totalScore2);           
        }
    }
    
    static void bestEconomy(List<String>uniqueBowlingTeam, int[][] deliveriesData,int deliveriesRowCount, List<String> uniqueYear) {
        int i,k,j = 0;
        int uniqueBowlingTeamLen = uniqueBowlingTeam.size();
        // Array to store Year economy rate, Bowler Index
        float[][] economyRateDataResult = new float[uniqueBowlingTeamLen][3];
        int[][] economyRateData1 = new int[uniqueBowlingTeamLen][5];
        HashMap<Integer, Integer> economyRateData = new HashMap<>();
        HashMap<Integer, Integer> economyRateDataYear = new HashMap<>();
        HashMap<Integer, Integer> oversBowled = new HashMap<>();
        
        int Year;
        for(i=1;i<uniqueYear.size();i++) {
            Year = Integer.parseInt(uniqueYear.get(i));
            for(j = 1; j < deliveriesRowCount; j++) {
                if(Year == deliveriesData[j][11]) {
                    // key is from uniqueTeamBowler List
                    int key = deliveriesData[j][10];
                    
                    int value = deliveriesData[j][7] + deliveriesData[j][4] +
                            deliveriesData[j][6] + deliveriesData[j][8] + deliveriesData[j][9];
                    
                    value = value + (economyRateData.get(key) == null ? 0 : economyRateData.get(key));
                    
                    int NballBowled;
                    NballBowled = (oversBowled.get(key) == null ? 0 : oversBowled.get(key)) + 1;
                    economyRateData.put(key,value);
                    economyRateDataYear.put(key, Year);
                    oversBowled.put(key, NballBowled);
                }
            }
                for(j = 0; j < economyRateDataYear.size(); j++) {
                    float economyRate;
                    if(Year == economyRateDataYear.get(j)) {
                        economyRate = oversBowled.get(j)/6;
                        economyRate = economyRateData.get(j)/economyRate;
//                        System.out.println("k: "+ j + " , Year: " + Year + " , Player Name: " + uniqueBowlingTeam.get(j) +
//                            " , Economy Rate: " + economyRate + " , Overs: " + oversBowled.get(j)/6);
                        if((oversBowled.get(j)/6) > 10) {
                            economyRateDataResult[j][0] = Year;
                            economyRateDataResult[j][1] = j;
                            economyRateDataResult[j][2] = economyRate;
                        }
                    }
                }
//            }
            economyRateData.clear();
            oversBowled.clear();
            
        }
        
        // sorting Economy Result Data Set
        Arrays.sort(economyRateDataResult, new java.util.Comparator<float[]>() {
            public int compare(float[] a, float[] b) {
                return Double.compare(a[2], b[2]);
            }
        });
        // initialized to get top10 players for an year
         int top10;
        for(i=1; i < uniqueYear.size(); i++) {
            Year = Integer.parseInt(uniqueYear.get(i));
            top10 = 1;
            System.out.print("******************Year: " + Year + "***********************\n");
            for(j = economyRateDataResult.length - 1; j >= 0;j--) {
                if(Year == economyRateDataResult[j][0] && top10 <= 10) {
                    top10++;
                    int index = Math.round(economyRateDataResult[j][1]);
                    System.out.println("Year: " + Year + " , Player: " + uniqueBowlingTeam.get(index) + " , Economy: " + economyRateDataResult[j][2]);
                }
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String line;
        String cvsSplitBy = ",";
        int coun;
        
        int deliveriesRowCount;
        String fileName= "matches.csv";
        
        String fileNameDeliveries= "deliveries.csv";
        
        // loading data into two multidimensional Arrays
        InputStream is = new BufferedInputStream(new FileInputStream(fileName));
        InputStream isd = new BufferedInputStream(new FileInputStream(fileNameDeliveries));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int Dcount = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            while ((readChars = isd.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++Dcount;
                    }
                }
            }
            coun = (count == 0 && !empty) ? 1 : count;
            
            deliveriesRowCount = (Dcount == 0 && !empty) ? 1 : Dcount;
            
            //System.out.println("Count: " + coun);
            //System.out.println("Deliverable Count: " + deliveriesRowCount);
            } finally {
            is.close();
        }
        
        String[][] arr =new String[coun][7];
        int[][] deliveriesData =new int[deliveriesRowCount][12];
        
        // Convert String Array to List
        List<String> uniqueTeam = new ArrayList();
        
        // unique bowling Team
        List<String> uniqueBowlingTeam = new ArrayList();
        
        //Unique Year
        List<String> uniqueYear = new ArrayList();
        
        int incrementer = 0;
      
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            
            BufferedReader brD = new BufferedReader(new FileReader(fileNameDeliveries));
            
            
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] cols = line.split(cvsSplitBy);
                
                arr[incrementer][0] = cols[1];
                arr[incrementer][1] = cols[4];
                arr[incrementer][2] = cols[5];
                arr[incrementer][3] = cols[6];
                arr[incrementer][4] = cols[7];
                arr[incrementer][5] = cols[9];
                arr[incrementer][6] = cols[0];
                incrementer++;
                
                String kk = cols[4];
                // insert unique team list
                
                if(!uniqueTeam.contains(cols[4])){
                    uniqueTeam.add(kk);
                }
                //loading unique years
                if(!uniqueYear.contains(cols[1])){
                    uniqueYear.add(cols[1]);
                }
            }
            int iteration = 0;
            incrementer = 1;
            while ((line = brD.readLine()) != null) {
                // use comma as separator
                String[] cols = line.split(cvsSplitBy);
                
                // skipping headers of csv into array
                if(iteration == 0) {
                    iteration++;  
                    continue;
                } else {
                    deliveriesData[incrementer][0] = Integer.parseInt(cols[0]);
                    deliveriesData[incrementer][1] = Integer.parseInt(cols[1]);
                    deliveriesData[incrementer][2] = Integer.parseInt(cols[4]);
                    deliveriesData[incrementer][3] = Integer.parseInt(cols[5]);
                    
                    //bats man runs
                    deliveriesData[incrementer][4] = Integer.parseInt(cols[13]);
                    // total runs
                    deliveriesData[incrementer][5] = Integer.parseInt(cols[15]);
                    
                    // wide runs
                    deliveriesData[incrementer][6] = Integer.parseInt(cols[8]);
                    //No Ball Runs
                    deliveriesData[incrementer][7] = Integer.parseInt(cols[11]);
                    //Penalty runs
                    deliveriesData[incrementer][8] = Integer.parseInt(cols[12]);
                    //Extra Runs
                    deliveriesData[incrementer][9] = Integer.parseInt(cols[14]);
                    
                    //System.out.println("Column 0: " + deliveriesData[incrementer][0] + " , Column 1: " + deliveriesData[incrementer][1] + " , Column 4: " + deliveriesData[incrementer][2] + " , Column 5: " + deliveriesData[incrementer][3] + " , Column 13: " + deliveriesData[incrementer][4] + " , Column 15: " + deliveriesData[incrementer][5]);
                    
                    
                    //loading unique bowlers list
                    String kk = cols[7];
                    // insert unique team list
                
                    if(!uniqueBowlingTeam.contains(kk)){
                        uniqueBowlingTeam.add(kk);
                    }
                    //loading bowling team index
                    deliveriesData[incrementer][10] = uniqueBowlingTeam.indexOf(kk);
                    
                    incrementer++;
                    
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
        int i, count = 0;
        
        int uniqueTeamLen;
        
        uniqueTeamLen = uniqueTeam.size();
        
        int[] counter = new int[uniqueTeamLen];
        
        int[] top4 = new int[5];
        String Year;
        
        for(int year = 2016; year <= 2017; year++) {
            Year = Integer.toString(year);
            for (i=1; i < coun; i++) {            
                String field = "field";
                if ( Year.equals(arr[i][0]) && field.equals(arr[i][4])) {
                    int index = uniqueTeam.indexOf(arr[i][3]);                
                    counter[index]++;
                } 
            }
            
            System.out.println("\nFirst Question\n");
            
            top(counter, top4, uniqueTeamLen, uniqueTeam, Year);
            for(int k = 0;k < uniqueTeamLen; k++){
                counter[k] = 0;
            }                      
        }
        
        System.out.println("Second Question");
        analyzeScore(deliveriesData, deliveriesRowCount, coun, uniqueTeam, arr);
        
        System.out.println("\nThird Question\n");
        bestEconomy(uniqueBowlingTeam, deliveriesData, deliveriesRowCount, uniqueYear);
    }  
}