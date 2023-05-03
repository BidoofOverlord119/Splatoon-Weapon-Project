def test1():
    test_vals=[23,25,46,50]
    #for damage_reducer in range(100):
    damage_reducer=9
    base_damage = 1500

    # Distance thresholds multiplied by 10
    distance_thresh_1=42
    distance_thresh_2 = 72
    distance_thresh_end=107
    yes = False
    min_damage = 350

    currentdamage=0
    damage_dealt = {}
    for i in range(120):
        if i<distance_thresh_1:
            currentdamage=base_damage
        elif i == distance_thresh_1:
            currentdamage=base_damage
        if 42<i<(distance_thresh_end+1):
            currentdamage-=damage_reducer
        if currentdamage==1000 and i==distance_thresh_2:
            #print(f"{i} {currentdamage} {damage_reducer}")
            yes=True
        if i==72:
            print(f"{currentdamage}: {damage_reducer}")
        if i>distance_thresh_end:
            currentdamage=min_damage
        
        damage_dealt[i]=(currentdamage)

    if yes:
        print(f"{damage_reducer}:  {damage_dealt}")

test1()

def test2():
    #for damage_reduct in range(100):
    damage_reduct=0.9
    base_damage = 1500
    min_damage = 350
    current_damage=1500
    good1 = False
    good2 = False
    damage_reduct2 = damage_reduct
    damage_vals = {}
    for i in range(66):
        if i == 30 and current_damage==1000:
            print("Yes.")
            good1=True
        if i == 65 and current_damage == 350:
            print("Success 2.")
            good2=True
        current_damage-=damage_reduct2
        #damage_reduct2*=2
        damage_vals[i] = current_damage
    if good1 or good2:
        print(f"{damage_reduct}:  {damage_vals}")

