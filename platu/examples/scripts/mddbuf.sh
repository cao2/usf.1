#!/bin/bash
CFG="examples/cfg/mddbuf.cfg"
DEST="examples/log/mddbuf"
MEMSET="-Xmx3000m"

echo running arbN5
time java $MEMSET -jar Platu.jar examples/arb/N5.cmnd $CFG > $DEST/arbN5.txt 2> $DEST/arbN5err.txt
date
sleep 5
echo running arbN7
time java $MEMSET -jar Platu.jar examples/arb/N7.cmnd $CFG > $DEST/arbN7.txt 2> $DEST/arbN7err.txt
date
sleep 5
echo running arbN9
time java $MEMSET -jar Platu.jar examples/arb/N9.cmnd $CFG > $DEST/arbN9.txt 2> $DEST/arbN9err.txt
date
sleep 5

echo running fifoN8
time java $MEMSET -jar Platu.jar examples/fifo/N8.cmnd $CFG > $DEST/fifoN8.txt 2> $DEST/fifoN8err.txt
date
sleep 5
echo running fifoN10
time java $MEMSET -jar Platu.jar examples/fifo/N10.cmnd $CFG > $DEST/fifoN10.txt 2> $DEST/fifoN10err.txt
date
sleep 5

echo running dmeN3
time java $MEMSET -jar Platu.jar examples/dme/N3.cmnd $CFG > $DEST/dmeN3.txt 2> $DEST/dmeN3err.txt
date
sleep 5
echo running dmeN4
time java $MEMSET -jar Platu.jar examples/dme/N4.cmnd $CFG > $DEST/dmeN4.txt 2> $DEST/dmeN4err.txt
date
sleep 5
echo running dmeN5
time java $MEMSET -jar Platu.jar examples/dme/N5.cmnd $CFG > $DEST/dmeN5.txt 2> $DEST/dmeN5err.txt
date
sleep 5

echo running pipectrl
time java $MEMSET -jar Platu.jar examples/pipectrl/pipectrl.cmnd $CFG > $DEST/pipectrl.txt 2> $DEST/pipectrlerr.txt
date
sleep 5

echo running tagunit
time java $MEMSET -jar Platu.jar examples/tagunit/tu.cmnd $CFG > $DEST/tagunit.txt 2> $DEST/taguniterr.txt
date
sleep 5

echo running anderson.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/anderson/6.cmnd $CFG > $DEST/anderson6.txt 2> $DEST/anderson6err.txt
date
sleep 5

echo running anderson.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/anderson/8.cmnd $CFG > $DEST/anderson8.txt 2> $DEST/anderson8err.txt
date
sleep 5

echo running at.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/4.cmnd $CFG > $DEST/at4.txt 2> $DEST/at4err.txt
date
sleep 5

ehco running at.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/5.cmnd $CFG > $DEST/at5.txt 2> $DEST/at5err.txt
date
sleep 5

echo running at.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/6.cmnd $CFG > $DEST/at6.txt 2> $DEST/at6err.txt
date
sleep 5

echo running at.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/at/7.cmnd $CFG > $DEST/at7.txt 2> $DEST/at7err.txt
date
sleep 5


echo running bakery.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/5.cmnd $CFG > $DEST/bakery6.txt 2> $DEST/bakery6err.txt
date
sleep 5

echo running bakery.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/6.cmnd $CFG > $DEST/bakery6.txt 2> $DEST/bakery6err.txt
date
sleep 5

echo running bakery.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/7.cmnd $CFG > $DEST/bakery7.txt 2> $DEST/bakery7err.txt
date
sleep 5

echo running bakery.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/bakery/8.cmnd $CFG > $DEST/bakery8.txt 2> $DEST/bakery8err.txt
date
sleep 5


echo running drivingPhils.3
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/3.cmnd $CFG > $DEST/drivPhil3.txt 2> $DEST/drivPhil3err.txt
date
sleep 5

echo running drivingPhils.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/4.cmnd $CFG > $DEST/drivPhil4.txt 2> $DEST/drivPhil4err.txt
date
sleep 5

echo running drivingPhils.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/drivingPhils/5.cmnd $CFG > $DEST/drivPhil5.txt 2> $DEST/drivPhil5err.txt
date
sleep 5

echo running fischer.3
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/3.cmnd $CFG > $DEST/fischer3.txt 2> $DEST/fischer3err.txt
date
sleep 5

echo runing fischer.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/4.cmnd $CFG > $DEST/fischer4.txt 2> $DEST/fischer4err.txt
date
sleep 5

echo running fischer.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/5.cmnd $CFG > $DEST/fischer5.txt 2> $DEST/fischer5err.txt
date

echo running fischer.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/6.cmnd $CFG > $DEST/fischer6.txt 2> $DEST/fischer6err.txt
date
sleep 5

echo running fischer.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/fischer/7.cmnd $CFG > $DEST/fischer7.txt 2> $DEST/fischer7err.txt
date
sleep 5

echo running lamport.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/5.cmnd $CFG > $DEST/lamport5.txt 2> $DEST/lamport5err.txt
date
sleep 5

echo running lamport.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/6.cmnd $CFG > $DEST/lamport6.txt 2> $DEST/lamport6err.txt
date
sleep 5

echo running lamport.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/7.cmnd $CFG > $DEST/lamport7.txt 2> $DEST/lamport7err.txt
date
sleep 5

echo running lamport.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/lamport/8.cmnd $CFG > $DEST/lamport8.txt 2> $DEST/lamport8err.txt
date
sleep 5

echo running mcs.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/mcs/5.cmnd $CFG > $DEST/mcs5.txt 2> $DEST/mcs5err.txt
date
sleep 5


echo running peterson.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/4.cmnd $CFG > $DEST/peterson4.txt 2> $DEST/peterson4err.txt
date
sleep 5

echo running peterson.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/5.cmnd $CFG > $DEST/peterson5.txt 2> $DEST/peterson5err.txt
date
sleep 5

echo running peterson.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/6.cmnd $CFG > $DEST/peterson6.txt 2> $DEST/peterson6err.txt
date
sleep 5

echo running peterson.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/peterson/7.cmnd $CFG > $DEST/peterson7.txt 2> $DEST/peterson7err.txt
date
sleep 5


echo running phils.6
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/6.cmnd $CFG > $DEST/phils6.txt 2> $DEST/phils6err.txt
date
sleep 5

echo running phils.7
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/7.cmnd $CFG > $DEST/phils7.txt 2> $DEST/phils7err.txt
date
sleep 5

echo running phils.8
time java $MEMSET -jar Platu.jar examples/MutualExclusion/phils/8.cmnd $CFG > $DEST/phils8.txt 2> $DEST/phils8err.txt
date
sleep 5


echo running syzmanski.4
time java $MEMSET -jar Platu.jar examples/MutualExclusion/syzmanski/4.cmnd $CFG > $DEST/syzmanski4.txt 2> $DEST/syzmanski4err.txt
date
sleep 5

echo running syzmanski.5
time java $MEMSET -jar Platu.jar examples/MutualExclusion/syzmanski/5.cmnd $CFG > $DEST/syzmanski5.txt 2> $DEST/syzmanski5err.txt
date
sleep 5
