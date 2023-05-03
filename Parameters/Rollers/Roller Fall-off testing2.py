base_damage = 1500
min_damage = 350
current_damage=1500

frame_thresh_1 = 25

frame_thresh_2 = 45

damage_reduce_by_frame=5
for i in range(120):
    if 25<i<45:
        current_damage-=damage_reduce_by_frame
    print(f"{i}: {damage_reduce_by_frame} {current_damage}")