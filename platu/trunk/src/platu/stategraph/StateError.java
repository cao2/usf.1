package platu.stategraph;

/**
 * Enumeration used to denote whether a State contains an error and the type of error. 
 * Types include no error(NONE), disabling error (DISABLING), expression error (EXPRESSION), 
 * deadlock error (DEADLOCK), assertion error (ASSERTION), and livelock error (LIVELOCK).
 */
public enum StateError {
	NONE, // indicates no error
	DISABLING, // indicates a disabling error
	EXPRESSION, // indicates an expression error (division by 0 or array index out of bounds)
	DEADLOCK, // indicates a deadlock error
	LIVELOCK, // indicates a livelock error
	ASSERTION // indicates an assertian error
}
