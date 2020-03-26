#-*-coding:utf-8-*-

import RPi.GPIO as GPIO
import time

flag = 0
flag_1 = 0
count = 0

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

####### 핀 번호창 #######
TRIG = 23
ECHO = 24
BUTT_START = 19
BUTT_STOP = 26
SENSER_DC = 21

#######################


print("Distance measurement in progress")

########  설정창 ######
GPIO.setup(TRIG,GPIO.OUT)
GPIO.setup(ECHO,GPIO.IN)
GPIO.setup(BUTT_START,GPIO.IN)
GPIO.setup(BUTT_STOP,GPIO.IN)
GPIO.setup(SENSER_DC,GPIO.OUT)
GPIO.output(SENSER_DC,False)
#######################

while True:
    if GPIO.input(26) == GPIO.HIGH:
        GPIO.output(SENSER_DC,True)
        flag = 1
        print("start")
        break
    time.sleep(0.0001)

if (flag) == 1:
    GPIO.output(TRIG,False)
    print("waiting for sensor tho settle")
    time.sleep(2)

    try:

        while True:

            GPIO.output(TRIG,True)
            time.sleep(0.0001)
            GPIO.output(TRIG,False)

            while GPIO.input(ECHO) == 0:
                start = time.time()
            while GPIO.input(ECHO) == 1:
                stop = time.time()

            check_time = stop - start
            distance = check_time*34300 / 2
            print("%.1f"%distance)
            time.sleep(0.5)
            
           # if ( distance >= 50 or distance <=80 )
           #     count +=1 
           #     if(
        
    except KeyboardInterrupt:
        print("stopped by User")
        GPIO.cleanup()
