function func(code) {
	
	var options = {
  parse: {
    strict: false
  },
  compress: {
    sequences     : true,
    properties    : true,
    dead_code     : true,
    drop_debugger : true,
    unsafe        : true,
    unsafe_comps  : true,
    conditionals  : true,
    comparisons   : true,
    evaluate      : true,
    booleans      : true,
    loops         : true,
    unused        : true,
    hoist_funs    : true,
    hoist_vars    : false,
    if_return     : true,
    join_vars     : true,
    cascade       : true,
    side_effects  : true,
    negate_iife   : true,
    screw_ie8     : false,

    warnings      : true,
    global_defs   : {}
  },
  output: {
    indent_start  : 0,
    indent_level  : 4,
    quote_keys    : false,
    space_colon   : true,
    ascii_only    : false,
    inline_script : true,
    width         : 80,
    max_line_len  : 32000,
    beautify      : false,
    source_map    : null,
    bracketize    : false,
    semicolons    : true,
    comments      : "",
    preserve_line : false,
    screw_ie8     : false
  }
};

var default_options = {};
function uglify(code, options) {
	// Create copies of the options
	var parse_options = options.parse;
	var compress_options = options.compress;
	var output_options = options.output;

	// 1. Parse
	var toplevel_ast = UglifyJS.parse(code, parse_options);
	toplevel_ast.figure_out_scope();

	// 2. Compress
	var compressor = new UglifyJS.Compressor(compress_options);
	var compressed_ast = toplevel_ast.transform(compressor);

	// 3. Mangle
	compressed_ast.figure_out_scope();
	compressed_ast.compute_char_frequency();
	compressed_ast.mangle_names();

	// 4. Generate output
	code = compressed_ast.print_to_string(output_options);

	return code;
}

	var res = uglify(code, options);
	return res;

	return UglifyJS.parse(code).print_to_string({});

	
}