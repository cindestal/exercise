# coding:utf-8
'''
def set_passline(passline):
	def cmp(val): #逻辑判断相同
		if val > passline:
			print "pass"
		else:
			print "failed"
	return cmp

f_100 = set_passline(60)
f_150 = set_passline(90)

f_100(79)
f_150(79)


def dec(func):
	def in_dec(*arg):#逻辑判断相同
		print 'in dec arg:',arg
		if len(arg) == 0:
			return 0
		for a in arg:
			if not isinstance(a,int):
				return 0
		return func(*arg)
	return in_dec

#my_sum = dec(my_sum)
@dec
def my_sum(*arg):
	print 'in my_sum'
	return sum(arg)

def my_average(*arg):
	return sum(arg)/len(arg)

#my_average = dec(my_average)

print my_sum(1,2,3,4)
#print my_average(1,2,3,4)
#print my_sum()
#print my_average()


def deco(func):
	def in_deco(x,y):
		print "in in_deco"
		func(x,y)
		print 'after func'
	print "in deco"
	return in_deco

@deco
def Max(x,y):
	print "in Max"
	print 'max:',max(x,y)

Max(1,2)


def decor(func):
	def in_decor():
		print "in in_decor begin call"
		func()
		print "in in_decor end call"
	return in_decor

@decor
def fun():
	print "in fun calling..."

fun()
'''

import functools
def log(text):
	def dec(func):
		@functools.wraps(func)
		def in_dec(*args):
			if callable(text):
				print 'text calling:',text.__name__
			else:
				print 'text:',text,'function:',func.__name__
			func(*args)
		return in_dec
	if callable(text):
		return dec(text)
	else:
		return dec


@log
def no_arg():
	print 'no text here'

@log('exercise')
def have_arg():
	print 'text is "exercise"'

have_arg()
no_arg()

int2 = functools.partial(int,base=2)
print int2('1000000')
'''

def log(text):
    def decorator(func):
        def wrapper(*args, **kw):
            print '%s %s():' % (text, func.__name__)
            return func(*args, **kw)
        return wrapper
    return decorator

@log('execute')
def now():
    print '2013-12-25'
now()
'''