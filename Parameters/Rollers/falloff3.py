base_damage = 1000

curr=base_damage
damage_reduction = 5

'''
Simulating the exponential growth from 7.2 to 10.7
'''

for i in range(35):
    curr-=damage_reduction
    damage_reduction+=0.5

print(curr)