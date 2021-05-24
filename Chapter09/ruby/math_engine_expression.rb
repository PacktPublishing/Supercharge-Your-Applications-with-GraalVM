require 'rubygems'
require 'math_engine'

def eval(exp)
    engine = MathEngine.new
    ret = engine.evaluate(exp)
    
    puts(ret)
    return ret.truncate(4).to_f()
end    

Polyglot.export_method('eval')
