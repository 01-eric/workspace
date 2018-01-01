################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../list.cpp 

OBJS += \
./list.o 

CPP_DEPS += \
./list.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cygwin C++ Compiler'
	g++ -I../usr/include -I../usr/include/c++/4.6 -I../usr/include/c++/4.6/bits -I../usr/include/i386-linux-gnu -I../usr/include/i386-linux-gnu/bits -I../usr/include/c++/4.6/debug -I../usr/include/c++/4.6/i686-linux-gnu -I../usr/include/c++/4.6/i686-linux-gnu/bits -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


