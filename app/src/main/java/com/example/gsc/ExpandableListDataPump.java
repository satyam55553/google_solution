package com.example.gsc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExpandableListDataPump {
    public static Map<String, List<String>> getData() {
        Map<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> mod1 = new ArrayList<String>();
        mod1.add("Characteristics and Design metrics of Embedded system");
        mod1.add("Real time systems:Need for Real-time systems");
        mod1.add("Hard-Soft Real-time systems");
        mod1.add("Challenges in Embedded system Design: Power, Speed and Code density");

        List<String> mod2 = new ArrayList<String>();
        mod2.add("Embedded cores");
        mod2.add("Types of memories");
        mod2.add("Sensors (Optical encoders,Resistive)");
        mod2.add("Actuators (Solenoid valves, Relay/switch, Opto-couplers)");
        mod2.add("Power supply considerations in Embedded systems:");
        mod2.add("Low power features- Idle & Power down mode");
        mod2.add("Sleep mode");
        mod2.add("Brown-out detection");
        mod2.add("Communication Interfaces: \n" + "Comparative study of serial communication " +
                "interfaces (RS-232, RS-485), I2C, CAN, USB (v2.0), Bluetooth, Zig-Bee");
        mod2.add("Selection criteria of above interfaces");

        List<String> mod3 = new ArrayList<String>();
        mod3.add("Program Modelling concepts: DFG,FSM,UML");
        mod3.add("Embedded C-programming concepts (from Embedded system point of \n" +
                "view): Data types, Modifiers, Qualifiers, Functions, Macros, Interrupt \n" +
                "service routine, Device drivers.");
        mod3.add("Real-time Operating system: Need of RTOS in Embedded system software \n" +
                "and comparison with GPOS, Foreground/Background processes, Interrupt \n" +
                "latency, Task, Task states, Multi-tasking, Context switching, Task \n" +
                "scheduling, Scheduling algorithms-Rate Monotonic Scheduling, Earliest \n" +
                "Deadline First (with numericals), Inter-process communication: Semaphore, \n" +
                "Mailbox, Message queues, Event timers, Task synchronisation- Shared \n" +
                "data, Priority inversion, Deadlock. \n" +
                "Memory Management");
        mod3.add("Introduction to µCOS II RTOS: Study of Kernel structure of µCOS II, \n" +
                "µCOS II functions for Initialisation, Task creation, Inter-task \n" +
                "communication and Resource management, Memory management");

        List<String> mod4 = new ArrayList<String>();
        mod4.add("Embedded Product Design Life-Cycle (EDLC)");
        mod4.add("Hardware-Software Co-design");
        mod4.add("Testing & Debugging: Boundary-scan/JTAG interface concepts, Black-Box \n" +
                "testing, White-Box testing, Hardware emulation, Logic analyser.");

        List<String> mod5 = new ArrayList<String>();
        mod5.add("Soft Real-time: Automatic Chocolate Vending machine using µCOS II \n" +
                "RTOS- Requirements study, Specification study using UML, Hardware \n" +
                "architecture, Software architecture");
        mod5.add("Hard Real-time: Car Cruise-Control using µCOS II RTOS- Requirements \n" +
                "study, specification study using UML, Hardware architecture, Software \n" +
                "Architecture");

//        List<String> mod6 = new ArrayList<String>();
//        mod6.add("");
//        mod6.add("");
//        mod6.add("");

        expandableListDetail.put("Introduction to Embedded Systems", mod1);
        expandableListDetail.put("Embedded Hardware", mod2);
        expandableListDetail.put("Embedded Software", mod3);
        expandableListDetail.put("System Integration,Testing and Debugging Methodology", mod4);
        expandableListDetail.put("Case Studies", mod5);
//        expandableListDetail.put("", mod6);
        return expandableListDetail;
    }
}
