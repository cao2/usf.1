#!/bin/bash
CFG="-cfg examples/cfg/base.cfg"
DEST="examples/log/decomposedState"
MEMSET="-Xmx3000m"

echo running arbN5
time java $MEMSET -jar Platu.jar examples/arb/arbN5.lpn $CFG > $DEST/arbN5.txt 2> $DEST/arbN5err.txt
date
sleep 5
echo running arbN7
time java $MEMSET -jar Platu.jar examples/arb/arbN7.lpn $CFG > $DEST/arbN7.txt 2> $DEST/arbN7err.txt
date
sleep 5
echo running arbN9
time java $MEMSET -jar Platu.jar examples/arb/arbN9.lpn $CFG > $DEST/arbN9.txt 2> $DEST/arbN9err.txt
date
sleep 5

echo running fifoN8
time java $MEMSET -jar Platu.jar examples/fifo/fifoN8.lpn $CFG > $DEST/fifoN8.txt 2> $DEST/fifoN8err.txt
date
sleep 5
echo running fifoN10
time java $MEMSET -jar Platu.jar examples/fifo/fifoN10.lpn $CFG > $DEST/fifoN10.txt 2> $DEST/fifoN10err.txt
date
sleep 5

echo running dmeN3
time java $MEMSET -jar Platu.jar examples/dme/dmeN3.lpn $CFG > $DEST/dmeN3.txt 2> $DEST/dmeN3err.txt
date
sleep 5
echo running dmeN4
time java $MEMSET -jar Platu.jar examples/dme/dmeN4.lpn $CFG > $DEST/dmeN4.txt 2> $DEST/dmeN4err.txt
date
sleep 5
echo running dmeN5
time java $MEMSET -jar Platu.jar examples/dme/dmeN5.lpn $CFG > $DEST/dmeN5.txt 2> $DEST/dmeN5err.txt
date
sleep 5

echo running pipectrl
time java $MEMSET -jar Platu.jar examples/pipectrl/pipectrl.lpn $CFG > $DEST/pipectrl.txt 2> $DEST/pipectrlerr.txt
date
sleep 5

echo running tagunit
time java $MEMSET -jar Platu.jar examples/tagunit/tagunit.lpn $CFG > $DEST/tagunit.txt 2> $DEST/taguniterr.txt
date
sleep 5

echo running mmu
time java $MEMSET -jar Platu.jar examples/mmu/mmu_v1.lpn $CFG > $DEST/mmu.txt 2> $DEST/mmuerr.txt
date
sleep 5

echo running anderson.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/anderson/anderson.6.lpn $CFG > $DEST/anderson6.txt 2> $DEST/anderson6err.txt
date
sleep 5

echo running anderson.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/anderson/anderson.8.lpn $CFG > $DEST/anderson8.txt 2> $DEST/anderson8err.txt
date
sleep 5

echo running at.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/at.4.lpn $CFG > $DEST/at4.txt 2> $DEST/at4err.txt
date
sleep 5

ehco running at.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/at.5.lpn $CFG > $DEST/at5.txt 2> $DEST/at5err.txt
date
sleep 5

echo running at.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/at.6.lpn $CFG > $DEST/at6.txt 2> $DEST/at6err.txt
date
sleep 5

echo running at.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/at.7.lpn $CFG > $DEST/at7.txt 2> $DEST/at7err.txt
date
sleep 5


echo running bakery.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/bakery.5.lpn $CFG > $DEST/bakery6.txt 2> $DEST/bakery6err.txt
date
sleep 5

echo running bakery.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/bakery.6.lpn $CFG > $DEST/bakery6.txt 2> $DEST/bakery6err.txt
date
sleep 5

echo running bakery.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/bakery.7.lpn $CFG > $DEST/bakery7.txt 2> $DEST/bakery7err.txt
date
sleep 5

echo running bakery.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/bakery.8.lpn $CFG > $DEST/bakery8.txt 2> $DEST/bakery8err.txt
date
sleep 5


echo running drivingPhils.3
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/drivingPhils.3.lpn $CFG > $DEST/drivPhil3.txt 2> $DEST/drivPhil3err.txt
date
sleep 5

echo running drivingPhils.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/drivingPhils.4.lpn $CFG > $DEST/drivPhil4.txt 2> $DEST/drivPhil4err.txt
date
sleep 5

echo running drivingPhils.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/drivingPhils.5.lpn $CFG > $DEST/drivPhil5.txt 2> $DEST/drivPhil5err.txt
date
sleep 5

echo running fischer.3
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/fischer.3.lpn $CFG > $DEST/fischer3.txt 2> $DEST/fischer3err.txt
date
sleep 5

echo runing fischer.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/fischer.4.lpn $CFG > $DEST/fischer4.txt 2> $DEST/fischer4err.txt
date
sleep 5

echo running fischer.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/fischer.5.lpn $CFG > $DEST/fischer5.txt 2> $DEST/fischer5err.txt
date

echo running fischer.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/fischer.6.lpn $CFG > $DEST/fischer6.txt 2> $DEST/fischer6err.txt
date
sleep 5

echo running fischer.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/fischer.7.lpn $CFG > $DEST/fischer7.txt 2> $DEST/fischer7err.txt
date
sleep 5

echo running lamport.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/lamport.5.lpn $CFG > $DEST/lamport5.txt 2> $DEST/lamport5err.txt
date
sleep 5

echo running lamport.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/lamport.6.lpn $CFG > $DEST/lamport6.txt 2> $DEST/lamport6err.txt
date
sleep 5

echo running lamport.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/lamport.7.lpn $CFG > $DEST/lamport7.txt 2> $DEST/lamport7err.txt
date
sleep 5

echo running lamport.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/lamport.8.lpn $CFG > $DEST/lamport8.txt 2> $DEST/lamport8err.txt
date
sleep 5

echo running mcs.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/mcs/mcs.5.lpn $CFG > $DEST/mcs5.txt 2> $DEST/mcs5err.txt
date
sleep 5


echo running peterson.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/peterson.4.lpn $CFG > $DEST/peterson4.txt 2> $DEST/peterson4err.txt
date
sleep 5

echo running peterson.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/peterson.5.lpn $CFG > $DEST/peterson5.txt 2> $DEST/peterson5err.txt
date
sleep 5

echo running peterson.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/peterson.6.lpn $CFG > $DEST/peterson6.txt 2> $DEST/peterson6err.txt
date
sleep 5

echo running peterson.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/peterson.7.lpn $CFG > $DEST/peterson7.txt 2> $DEST/peterson7err.txt
date
sleep 5


echo running phils.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/phils.6.lpn $CFG > $DEST/phils6.txt 2> $DEST/phils6err.txt
date
sleep 5

echo running phils.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/phils.7.lpn $CFG > $DEST/phils7.txt 2> $DEST/phils7err.txt
date
sleep 5

echo running phils.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/phils.8.lpn $CFG > $DEST/phils8.txt 2> $DEST/phils8err.txt
date
sleep 5


echo running syzmanski.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/syzmanski/syzmanski.4.lpn $CFG > $DEST/syzmanski4.txt 2> $DEST/syzmanski4err.txt
date
sleep 5

echo running syzmanski.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/syzmanski/syzmanski.5.lpn $CFG > $DEST/syzmanski5.txt 2> $DEST/syzmanski5err.txt
date
sleep 5
