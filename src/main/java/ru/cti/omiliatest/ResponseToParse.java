package ru.cti.omiliatest;

import java.lang.reflect.Array;

public class ResponseToParse {

    String dialog_id;       //  The dialog id returned from "Start a new dialog" request
    Integer step;           //  Dialog step number
    String utterance;       //  User’s utterance
    String prompt;          //  Prompt of the system. This can also be found in "Action" field
    String prompt_duration; //  The duration of the prompt
    String sensitivity;     //  Step’s sensitivity level in case of DiaManT elicit’s sensitive information from the user
    Long step_timestamp;    //  Step’s start time
    String locale;          //  Dialog’s locale
    String dialog_metadata; //  Dialog’s metadata (See Dialog Metadata)
    Object semantic_interpretation;  // The semantic interpretion return by NLU (See _Semantic Interpretation)
    Array fields;           //  List of fields in DiaManT’s context (See Fields)
    Array trackers;         //  The list of event trackers triggered at this step (See Trackers)
    Object action;          //  The system action (e.g. request, confirmation, disambiguation etc.)
                            //  that will be executed at this step (See. Action)
    Array dialog_step_events;// The list of dialog events logged so far providing a summary of the interaction
                            //  at that point (See Dialog Events)
    Object grammars;        //  ASR and NLU recognition grammars (See Grammars)
    Object handoff_data;    //  Data sent on transfer (See HandOff)
    Object intent;          //  The identified intent (See Intent)
    Object target;          //  The active dialog target at this step. Targets correspond to the conversational goals
                            //  and tasks that the application fulfills (See Target)
    Object asr_data;        //  Reserved. Contains Omilia’s ASR data
}
